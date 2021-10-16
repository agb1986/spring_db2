package com.agb1986.springdb2.controllers;

import com.agb1986.springdb2.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/employee")
    public ResponseEntity<Object> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{employeeNumber}")
    public ResponseEntity<Object> getEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        return new ResponseEntity<>(employeeService.getEmployeeByEmployeeNumber(employeeNumber), HttpStatus.OK);
    }
}
