package com.example.CheckoutStaffPlanner.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Employee implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isAdmin;

    private String password;

    @ManyToMany
    private List<Wishbook> wishbooks;

    @ManyToMany
    private List<Schedule> schedule;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wishbook> getWishbooks() {
        return wishbooks;
    }

    public void setWishbooks(List<Wishbook> wishbooks) {
        this.wishbooks = wishbooks;
    }

    public Employee(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Employee(){}

    @Override
    public String toString() {
        return "Employee{" +
                "id = " + id +
                ", name = " + name +
                ", is admin = " + isAdmin +
                ", password = " + password +
                "}";
    }

}
