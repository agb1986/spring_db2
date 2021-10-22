package com.agb1986.employeeservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.agb1986.employeeservice.models.EmployeeModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TestEmployeeRespository {
    @Autowired
    private EmployeeRespository employeeRespository;

    private static EmployeeModel employeeModel;

    @BeforeEach
    void beforeEach() {
        employeeModel = new EmployeeModel();
        employeeModel.setEmployeeNumber("000010");
        employeeModel.setEmployeeFirstName("CHRISTINE");
        employeeModel.setEmployeeMiddleInital('I');
        employeeModel.setEmployeeLastName("HAAS");
        employeeModel.setEmployeeWorkDepartment("A00");
        employeeModel.setEmployeePhoneNumber("3978");
        employeeRespository.save(employeeModel);
    }

    @Test
    void test_repository_find_by_employee_number() {
        EmployeeModel returnEmployeeModel =
                employeeRespository.findByEmployeeNumber(employeeModel.getEmployeeNumber()).get();
        assertEquals(employeeModel.getEmployeeNumber(), returnEmployeeModel.getEmployeeNumber());
    }

    @Test
    void test_repository_find_by_employee_first_name_containing() {
        EmployeeModel returnEmployeeModel = employeeRespository
                .findByEmployeeFirstNameContaining(employeeModel.getEmployeeFirstName()).get(0);
        assertEquals(employeeModel.getEmployeeFirstName(),
                returnEmployeeModel.getEmployeeFirstName());
    }

    @Test
    void test_repository_find_by_employee_last_name_containing() {
        EmployeeModel returnEmployeeModel = employeeRespository
                .findByEmployeeLastNameContaining(employeeModel.getEmployeeLastName()).get(0);
        assertEquals(employeeModel.getEmployeeLastName(),
                returnEmployeeModel.getEmployeeLastName());
    }

    @Test
    void test_repository_find_by_employee_work_department() {
        EmployeeModel returnEmployeeModel = employeeRespository
                .findByEmployeeWorkDepartment(employeeModel.getEmployeeWorkDepartment()).get(0);
        assertEquals(employeeModel.getEmployeeWorkDepartment(),
                returnEmployeeModel.getEmployeeWorkDepartment());
    }

    @AfterEach
    void afterEach() {
        employeeRespository.delete(employeeModel);
    }
}
