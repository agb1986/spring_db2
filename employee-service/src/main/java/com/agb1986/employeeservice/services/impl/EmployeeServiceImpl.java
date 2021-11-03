package com.agb1986.employeeservice.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.agb1986.employeeservice.models.EmployeeModel;
import com.agb1986.employeeservice.repositories.EmployeeRespository;
import com.agb1986.employeeservice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;

    public void setEmployeeRespository(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    public List<EmployeeModel> getAllEmployees() {
        return employeeRespository.findAll();
    }

    public EmployeeModel getEmployeeByEmployeeNumber(String employeeNumber) {
        return employeeRespository.findByEmployeeNumber(employeeNumber).orElse(null);
    }

    public List<EmployeeModel> searchEmployeesBy(Map<String, String> params) {
        List<EmployeeModel> firstNameResults = new ArrayList<>();
        List<EmployeeModel> lastNameResults = new ArrayList<>();

        if (params.containsKey("fName")) {
            firstNameResults = employeeRespository.findByEmployeeFirstNameContaining(params.get("fName"));
        }

        if (params.containsKey("lName")) {
            lastNameResults = employeeRespository.findByEmployeeLastNameContaining(params.get("lName"));
        }

        if (!firstNameResults.isEmpty() && !lastNameResults.isEmpty()) {
            lastNameResults.removeAll(firstNameResults);
            firstNameResults.addAll(lastNameResults);
            return firstNameResults;
        } else if (!firstNameResults.isEmpty() && lastNameResults.isEmpty()) {
            return firstNameResults;
        } else if (firstNameResults.isEmpty() && !lastNameResults.isEmpty()) {
            return lastNameResults;
        }

        return Collections.emptyList();
    }

    public List<EmployeeModel> getEmployeeByEmployeeWorkDepartment(String employeeWorkDepartment) {
        return employeeRespository.findByEmployeeWorkDepartment(employeeWorkDepartment);
    }
}
