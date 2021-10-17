package com.agb1986.springdb2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.agb1986.springdb2.models.EmployeeModel;
import com.agb1986.springdb2.repositories.EmployeeRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRespository employeeRespository;

    public List<EmployeeModel> getAllEmployees() {
        return employeeRespository.findAll();
    }

    public EmployeeModel getEmployeeByEmployeeNumber(String employeeNumber) {
        return employeeRespository.findByEmployeeNumber(employeeNumber).orElse(null);
    }

    public List<EmployeeModel> searchEmployeesBy(Map<String, String> params) {
        List<EmployeeModel> firstNameResults = new ArrayList<>();
        List<EmployeeModel> lastNameResults = new ArrayList<>();

        if (params.containsKey("fname")) {
            firstNameResults = employeeRespository.findByEmployeeFirstNameContaining(params.get("fname"));
        }

        if (params.containsKey("lname")) {
            lastNameResults = employeeRespository.findByEmployeeLastNameContaining(params.get("lname"));
        }

        if (!firstNameResults.isEmpty() && !lastNameResults.isEmpty()) {
            lastNameResults.removeAll(firstNameResults);
            firstNameResults.addAll(lastNameResults);
        } else if (!firstNameResults.isEmpty() && lastNameResults.isEmpty()) {
            return firstNameResults;
        } else if (firstNameResults.isEmpty() && !lastNameResults.isEmpty()) {
            return lastNameResults;
        }

        return firstNameResults;
    }

    public List<EmployeeModel> getEmployeeByEmployeeWorkDepartment(String employeeWorkDepartment) {
        return employeeRespository.findByEmployeeWorkDepartment(employeeWorkDepartment);
    }
}
