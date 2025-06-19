package com.example.CheckoutStaffPlanner.repository;

import com.example.CheckoutStaffPlanner.ShiftType;
import com.example.CheckoutStaffPlanner.model.Wishbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
public interface WishbookRepo extends JpaRepository<Wishbook, Long> {
    Wishbook findWishbookByDateAndShift(LocalDate date, ShiftType shift);
    List<Wishbook> findWishbooksByDate(LocalDate date);
}
