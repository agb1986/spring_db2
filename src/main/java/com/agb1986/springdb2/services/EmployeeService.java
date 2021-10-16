package com.agb1986.springdb2.services;

import java.util.List;

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
        return employeeRespository.findByEmployeeNumber(employeeNumber).get();
    }
}
