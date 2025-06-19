package com.example.CheckoutStaffPlanner.controller;

import com.example.CheckoutStaffPlanner.ShiftType;
import com.example.CheckoutStaffPlanner.model.Wishbook;
import com.example.CheckoutStaffPlanner.service.WishbookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/wishbook")
public class WishbookController {

    private final WishbookService wishbookService;


    public WishbookController(WishbookService wishbookService) {
        this.wishbookService = wishbookService;
    }

    @PostMapping("/add/{name}/{password}/{date}/{shift}")
    public ResponseEntity<Wishbook> addEmployeeToWishbook(@PathVariable("name") String name, @PathVariable("password") String password,
                                                          @PathVariable("date") LocalDate date, @PathVariable("shift") ShiftType shift){
        Wishbook wishbook = wishbookService.addEmployeeToWishbook(name, password, date, shift);
        return new ResponseEntity<>(wishbook, HttpStatus.CREATED);
    }

    @PostMapping("/addWishbook")
    public ResponseEntity<Wishbook> addWishBook(Wishbook wishbook){
        Wishbook newWishbook = wishbookService.addWishbook(wishbook);
        return new ResponseEntity<>(newWishbook, HttpStatus.CREATED);
    }
}
