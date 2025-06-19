package com.example.CheckoutStaffPlanner.service;

import com.example.CheckoutStaffPlanner.ShiftType;
import com.example.CheckoutStaffPlanner.exception.UserIsNotAdminException;
import com.example.CheckoutStaffPlanner.exception.WishbookException;
import com.example.CheckoutStaffPlanner.model.Employee;
import com.example.CheckoutStaffPlanner.model.Schedule;
import com.example.CheckoutStaffPlanner.model.Wishbook;
import com.example.CheckoutStaffPlanner.repository.ScheduleRepo;
import com.example.CheckoutStaffPlanner.repository.WishbookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final WishbookRepo wishbookRepo;
    private final EmployeeService employeeService;

    @Autowired
    public ScheduleService(ScheduleRepo scheduleRepo, WishbookRepo wishbookRepo, EmployeeService employeeService) {
        this.scheduleRepo = scheduleRepo;
        this.wishbookRepo = wishbookRepo;
        this.employeeService = employeeService;
    }

    public Schedule addToScheduleFromWishbook(Long adminId,String employeeName, String employeePassword,
                                              LocalDate date, ShiftType shift) throws UserIsNotAdminException {
        List<Wishbook> wishbooks = wishbookRepo.findWishbooksByDate(date);
        Employee admin = employeeService.findEmployeeById(adminId);
        Employee employee = employeeService.findEmployeeByNameAndPassword(employeeName, employeePassword);
        List<Schedule> schedules = scheduleRepo.findSchedulesByDate(date);
        boolean foundInWishbook = false;
        boolean foundInSchedule = false;
        Schedule newSchedule = new Schedule(date, shift, new ArrayList<>());
        if(this.checkIfAdminAndEmployeeExist(admin, employee) && admin.isAdmin()){
            for(Wishbook book : wishbooks) {
                foundInWishbook = checkIfEmployeeIsInWishbook(book, employee, shift);
                if (foundInWishbook) {
                    break;
                }
            }
            if (foundInWishbook) {
                for (Schedule schedule : schedules) {
                    foundInSchedule = checkIfEmployeeIsInSchedule(schedule, employee);
                    if (schedule.getShift() == shift) {
                        newSchedule = schedule;
                    }
                }
                if (!foundInSchedule && newSchedule.getEmployees().size() < 2 && !newSchedule.getEmployees().contains(employee)) {
                        newSchedule.getEmployees().add(employee);
                        scheduleRepo.save(newSchedule);
                }
            } else {
                throw new WishbookException("Employee not found in any wishbook for the specified date/shift");
            }
        }
        else{
            throw new UserIsNotAdminException("User with id " + adminId + " is not an admin.");
        }

        return newSchedule;
    }

    private boolean checkIfEmployeeIsInWishbook(Wishbook book, Employee employee, ShiftType shift) {
        for(Employee emp : book.getEmployees()) {
            if (Objects.equals(emp.getId(), employee.getId()) && shift == book.getShift()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfEmployeeIsInSchedule(Schedule schedule, Employee employee) {
        for(Employee emp : schedule.getEmployees()) {
            if (Objects.equals(emp.getId(), employee.getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfAdminAndEmployeeExist(Employee admin, Employee employee) {
        return (admin != null && admin.isAdmin() && !admin.getPassword().isEmpty()
                && employee != null && !employee.isAdmin() && !employee.getPassword().isEmpty());
    }
    public List<Schedule> getScheduleByDate(LocalDate date){
        return scheduleRepo.findSchedulesByDate(date);

    }
}


