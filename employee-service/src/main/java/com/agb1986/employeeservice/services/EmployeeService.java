package com.agb1986.employeeservice.services;

import java.util.List;
import java.util.Map;
import com.agb1986.employeeservice.models.EmployeeModel;

public interface EmployeeService {
    List<EmployeeModel> getAllEmployees();
    EmployeeModel getEmployeeByEmployeeNumber(String employeeNumber);
    List<EmployeeModel> searchEmployeesBy(Map<String, String> params);
    List<EmployeeModel> getEmployeeByEmployeeWorkDepartment(String employeeWorkDepartment);
}
