package com.example.CheckoutStaffPlanner.service;

import com.example.CheckoutStaffPlanner.exception.UserNotFoundException;
import com.example.CheckoutStaffPlanner.model.Employee;
import com.example.CheckoutStaffPlanner.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id){
       return employeeRepository.findEmployeeById(id)
               .orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found."));
    }

    public Employee findEmployeeByNameAndPassword(String name, String password){
        return employeeRepository.findEmployeeByNameAndPassword(name, password)
                .orElseThrow(() -> new UserNotFoundException("User with name " + name + " and password " + password + " was not found."));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
