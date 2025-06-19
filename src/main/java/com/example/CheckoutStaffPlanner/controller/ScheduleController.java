package com.example.CheckoutStaffPlanner.controller;

import com.example.CheckoutStaffPlanner.ShiftType;
import com.example.CheckoutStaffPlanner.exception.UserIsNotAdminException;
import com.example.CheckoutStaffPlanner.model.Schedule;
import com.example.CheckoutStaffPlanner.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add/{adminId}/{employeeName}/{employeePassword}/{date}/{shift}")
    public ResponseEntity<Schedule> addFromWishbookToSchedule
            (@PathVariable("adminId") Long adminId, @PathVariable("employeeName") String employeeName,
             @PathVariable("employeePassword") String employeePassword,
             @PathVariable("date") LocalDate date, @PathVariable("shift") ShiftType shift) throws UserIsNotAdminException {
            Schedule schedule = scheduleService.addToScheduleFromWishbook(adminId, employeeName, employeePassword, date, shift);
            return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @GetMapping("/find/{date}")
    public ResponseEntity<List<Schedule>> getScheduleByDate(@PathVariable("date") LocalDate date){
        List<Schedule> schedules = scheduleService.getScheduleByDate(date);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{date}/{shift}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("date") LocalDate date, @PathVariable("shift") ShiftType shift){
        scheduleService.deleteScheduleByDateAndShift(date, shift);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
