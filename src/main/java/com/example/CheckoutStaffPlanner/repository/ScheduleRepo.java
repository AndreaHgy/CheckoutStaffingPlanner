package com.example.CheckoutStaffPlanner.repository;

import com.example.CheckoutStaffPlanner.ShiftType;
import com.example.CheckoutStaffPlanner.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    List<Schedule> findSchedulesByDate(LocalDate date);
    void deleteScheduleByDateAndShift(LocalDate date, ShiftType shift);

}
