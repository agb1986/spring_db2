package com.agb1986.employeeservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeModel {

    @Id
    @GeneratedValue
    @Column(name = "EMPNO")
    private String employeeNumber;
    
    @Column(name = "FIRSTNME")
    private String employeeFirstName;
    
    @Column(name = "MIDINIT")
    private char employeeMiddleInital;
    
    @Column(name = "LASTNAME")
    private String employeeLastName;

    @Column(name = "WORKDEPT")
    private String employeeWorkDepartment;

    @Column(name = "PHONENO")
    private String employeePhoneNumber;
}
