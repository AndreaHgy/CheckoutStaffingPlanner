package com.example.CheckoutStaffPlanner.model;

import com.example.CheckoutStaffPlanner.ShiftType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Wishbook {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ShiftType shift;

    @ManyToMany
    private List<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ShiftType getShift() {
        return shift;
    }

    public void setShift(ShiftType shift) {
        this.shift = shift;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Wishbook(Long id, LocalDate date, ShiftType shift, List<Employee> employees) {
        this.id=id;
        this.date = date;
        this.shift = shift;
        this.employees = employees;
    }

    public Wishbook(){};
    public String toString(){
        return "Wishbook{" +
                "Id: " + id +
                " Employee: " + employees +
                " ,date: " + date +
                ", shift: " + shift;
    }
}
