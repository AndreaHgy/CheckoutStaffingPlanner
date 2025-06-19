package com.example.CheckoutStaffPlanner.exception;

public class UserIsNotAdminException extends RuntimeException {
    public UserIsNotAdminException(String s) {
        super(s);
    }
}
