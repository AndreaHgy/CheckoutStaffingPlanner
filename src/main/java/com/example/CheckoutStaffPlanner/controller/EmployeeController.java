package com.example.CheckoutStaffPlanner.controller;

import com.example.CheckoutStaffPlanner.model.Employee;
import com.example.CheckoutStaffPlanner.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{name}/{password}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("name") String name, @PathVariable("password") String password){
        Employee employee = employeeService.findEmployeeByNameAndPassword(name, password);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}/{password}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("name") String name, @PathVariable("password") String password){
        employeeService.deleteEmployeeByNameAndPassword(name, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
