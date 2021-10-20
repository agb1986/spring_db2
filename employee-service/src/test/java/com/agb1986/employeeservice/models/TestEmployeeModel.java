package com.agb1986.employeeservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEmployeeModel {
    private static EmployeeModel employeeModel;
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @BeforeEach
    void beforeEach() {
        employeeModel = new EmployeeModel();
        employeeModel.setEmployeeNumber("000010");
        employeeModel.setEmployeeFirstName("JOHN");
        employeeModel.setEmployeeMiddleInital('A');
        employeeModel.setEmployeeLastName("SMITH");
        employeeModel.setEmployeeWorkDepartment("A00");
        employeeModel.setEmployeePhoneNumber("1234");
    }

    @Test
    void test_employee_number() {
        // Positive
        String testEmployeeNumber = "000010";
        assertEquals(testEmployeeNumber, employeeModel.getEmployeeNumber());

        // Negative - Min Size
        String testEmployeeNumberLengthMin = "123";
        employeeModel.setEmployeeNumber(testEmployeeNumberLengthMin);
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employeeModel);
        assertEquals("size must be between 6 and 10", violations.iterator().next().getMessage());

        // Negative - Max Size
        String testEmployeeNumberLengthMax = "12345678910";
        employeeModel.setEmployeeNumber(testEmployeeNumberLengthMax);
        violations = validator.validate(employeeModel);
        assertEquals("size must be between 6 and 10", violations.iterator().next().getMessage());

        // Negative - Null Check
        employeeModel.setEmployeeNumber(null);
        violations = validator.validate(employeeModel);
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void test_employee_firstname() {
        // Positive
        String testEmployeeFirstName = "JOHN";
        assertEquals(testEmployeeFirstName, employeeModel.getEmployeeFirstName());

        // Negative - Lowercase
        employeeModel.setEmployeeFirstName(testEmployeeFirstName.toLowerCase());
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employeeModel);
        assertEquals("must match \"[A-Z]+\"", violations.iterator().next().getMessage());

        // Negative - Null Check
        employeeModel.setEmployeeFirstName(null);
        violations = validator.validate(employeeModel);
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void test_employee_middle_inital() {
        char testEmployeeMiddleInital = 'A';
        assertEquals(testEmployeeMiddleInital, employeeModel.getEmployeeMiddleInital());
    }

    @Test
    void test_employee_lastname() {
        // Positive
        String testEmployeeLastName = "SMITH";
        assertEquals(testEmployeeLastName, employeeModel.getEmployeeLastName());

        // Negative - Lowercase
        employeeModel.setEmployeeLastName(testEmployeeLastName.toLowerCase());
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employeeModel);
        assertEquals("must match \"[A-Z]+\"", violations.iterator().next().getMessage());

        // Negative - Null Check
        employeeModel.setEmployeeLastName(null);
        violations = validator.validate(employeeModel);
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void test_employee_work_department() {
        // Positive
        String testEmployeeWorkDepartment = "A00";
        assertEquals(testEmployeeWorkDepartment, employeeModel.getEmployeeWorkDepartment());

        // Negative - Pattern
        String testEmployeeWorkDepartmentPattern = "AA1";
        employeeModel.setEmployeeWorkDepartment(testEmployeeWorkDepartmentPattern);
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employeeModel);
        assertEquals("must match \"[A-Z][0-9]{2}\"", violations.iterator().next().getMessage());

        // Negative - Null Check
        employeeModel.setEmployeeWorkDepartment(null);
        violations = validator.validate(employeeModel);
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }

    @Test
    void test_employee_phone_number() {
        String testEmployeePhoneNumber = "1234";
        assertEquals(testEmployeePhoneNumber, employeeModel.getEmployeePhoneNumber());

        // Negative - Pattern
        String testEmployeePhoneNumberLetters = "ABCD";
        employeeModel.setEmployeePhoneNumber(testEmployeePhoneNumberLetters);
        Set<ConstraintViolation<EmployeeModel>> violations = validator.validate(employeeModel);
        assertEquals("must match \"[0-9]{4}\"", violations.iterator().next().getMessage());
        
        // Negative - Legth
        String testEmployeePhoneNumberLength = "12345";
        employeeModel.setEmployeePhoneNumber(testEmployeePhoneNumberLength);
        violations = validator.validate(employeeModel);
        assertEquals("must match \"[0-9]{4}\"", violations.iterator().next().getMessage());

        // Negative - Null Check
        employeeModel.setEmployeePhoneNumber(null);
        violations = validator.validate(employeeModel);
        assertEquals("must not be null", violations.iterator().next().getMessage());
    }
}
