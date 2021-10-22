package com.agb1986.employeeservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeModel {

    @Id
    @Column(name = "EMPNO")
    @NotNull
    @Size(min = 6, max = 10)
    private String employeeNumber;
    

    @Column(name = "FIRSTNME")
    @NotNull
    @Pattern(regexp = "[A-Z]+")
    private String employeeFirstName;
    
    @Column(name = "MIDINIT")
    @NotNull
    private char employeeMiddleInital;
    
    @Column(name = "LASTNAME")
    @NotNull
    @Pattern(regexp = "[A-Z]+")
    private String employeeLastName;

    @Column(name = "WORKDEPT")
    @NotNull
    @Pattern(regexp = "[A-Z][0-9]{2}")
    private String employeeWorkDepartment;

    @Column(name = "PHONENO")
    @NotNull
    @Pattern(regexp = "[0-9]{4}")
    private String employeePhoneNumber;
}
