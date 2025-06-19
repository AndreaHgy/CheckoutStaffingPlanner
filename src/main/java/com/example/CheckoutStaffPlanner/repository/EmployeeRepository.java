package com.example.CheckoutStaffPlanner.repository;

import com.example.CheckoutStaffPlanner.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByNameAndPassword(String name, String password);

    Optional<Employee> findEmployeeById(Long id);
}
