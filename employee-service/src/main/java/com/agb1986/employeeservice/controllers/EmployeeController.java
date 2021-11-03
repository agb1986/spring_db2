package com.agb1986.employeeservice.controllers;

import java.util.List;
import java.util.Map;

import com.agb1986.employeeservice.models.EmployeeModel;
import com.agb1986.employeeservice.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping(path = "/employee")
    public ResponseEntity<Object> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{employeeNumber}")
    public ResponseEntity<Object> getEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        EmployeeModel employeeModel = employeeService.getEmployeeByEmployeeNumber(employeeNumber);

        if (employeeModel != null) {
            return new ResponseEntity<>(employeeModel, HttpStatus.OK);
        }
        return new ResponseEntity<>("No employee found with: " + employeeNumber, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/employee/search")
    public ResponseEntity<Object> getEmployeeSearch(@RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            return new ResponseEntity<>("No Params Detected", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeService.searchEmployeesBy(params), HttpStatus.OK);
    }

    @GetMapping(path = "/employee/deparment/{employeeWorkDepartment}")
    public ResponseEntity<Object> getEmployeeByEmployeeWorkDepartment(@PathVariable String employeeWorkDepartment) {
        List<EmployeeModel> employeeModels = employeeService
                .getEmployeeByEmployeeWorkDepartment(employeeWorkDepartment);

        if (!employeeModels.isEmpty()) {
            return new ResponseEntity<>(employeeService.getEmployeeByEmployeeWorkDepartment(employeeWorkDepartment),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("No employees found with deparment: " + employeeWorkDepartment,
                HttpStatus.NOT_FOUND);
    }
}
