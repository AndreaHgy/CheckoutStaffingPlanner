package com.example.CheckoutStaffPlanner.service;

import com.example.CheckoutStaffPlanner.ShiftType;
import com.example.CheckoutStaffPlanner.exception.UserNotFoundException;
import com.example.CheckoutStaffPlanner.model.Employee;
import com.example.CheckoutStaffPlanner.model.Wishbook;
import com.example.CheckoutStaffPlanner.repository.WishbookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishbookService {

    private final WishbookRepo wishbookRepo;

    private final EmployeeService employeeService;

    @Autowired
    public WishbookService(WishbookRepo wishbookRepo, EmployeeService employeeService) {
        this.wishbookRepo = wishbookRepo;
        this.employeeService = employeeService;
    }

    public Wishbook addWishbook(Wishbook wishbook){
        return wishbookRepo.save(wishbook);
    }

    public Wishbook addEmployeeToWishbook(String name, String password, LocalDate date, ShiftType shift){
        Wishbook wishbook = wishbookRepo.findWishbookByDateAndShift(date, shift);
        Employee employee = employeeService.findEmployeeByNameAndPassword(name, password);
        if (employee != null && !employee.isAdmin()) {
            if (wishbook == null) {
                wishbook = new Wishbook();
                List<Employee> employeeList = new ArrayList<>();
                employeeList.add(employee);
                wishbook.setEmployees(employeeList);
                wishbook.setDate(date);
                wishbook.setShift(shift);
            } else {
                wishbook.getEmployees().add(employee);
                wishbook.setDate(date);
                wishbook.setShift(shift);
            }
            employee.getWishbooks().add(wishbook);
        }
        else throw new UserNotFoundException("User with name " + name + " and password " + password + " does not exist or is an admin and cannot be added to wishbook.");
        wishbookRepo.save(wishbook);
        employeeService.addEmployee(employee);

        return wishbook;
    }
}

