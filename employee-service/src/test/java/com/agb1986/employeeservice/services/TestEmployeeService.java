package com.agb1986.employeeservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.agb1986.employeeservice.models.EmployeeModel;
import com.agb1986.employeeservice.repositories.EmployeeRespository;
import com.agb1986.employeeservice.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestEmployeeService {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
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
    }

    @Test
    void test_employeeService_getAllEmployees() {
        List<EmployeeModel> employeeModelsMock =
                Arrays.asList(employeeModel, employeeModel, employeeModel);
        when(employeeRespository.findAll()).thenReturn(employeeModelsMock);
        List<EmployeeModel> employeeModelsReturned = employeeService.getAllEmployees();

        assertEquals(employeeModelsMock.size(), employeeModelsReturned.size());

        for (int i = 0; i < employeeModelsReturned.size(); i++) {
            assertEquals(employeeModelsMock.get(i), employeeModelsReturned.get(i));
        }
    }

    @Test
    void test_employeeService_getEmployeeByEmployeeNumber() {
        when(employeeRespository.findByEmployeeNumber(anyString()))
                .thenReturn(Optional.of(employeeModel));
        EmployeeModel employeeModelReturned =
                employeeService.getEmployeeByEmployeeNumber(anyString());
        assertEquals(employeeModel, employeeModelReturned);
    }

    @Test
    void test_employeeService_searchEmployeesBy_fName() {
        Map<String, String> params = Map.of("fName", employeeModel.getEmployeeFirstName());
        List<EmployeeModel> employeeModelsMock =
                Arrays.asList(employeeModel, employeeModel, employeeModel);
        when(employeeRespository
                .findByEmployeeFirstNameContaining(employeeModel.getEmployeeFirstName()))
                        .thenReturn(employeeModelsMock);
        List<EmployeeModel> employeeModelsReturned = employeeService.searchEmployeesBy(params);

        assertEquals(employeeModelsMock.size(), employeeModelsReturned.size());

        for (int i = 0; i < employeeModelsReturned.size(); i++) {
            assertEquals(employeeModelsMock.get(i), employeeModelsReturned.get(i));
        }
    }

    @Test
    void test_employeeService_searchEmployeesBy_lName() {
        Map<String, String> params = Map.of("lName", employeeModel.getEmployeeLastName());
        List<EmployeeModel> employeeModelsMock =
                Arrays.asList(employeeModel, employeeModel, employeeModel);
        when(employeeRespository
                .findByEmployeeLastNameContaining(employeeModel.getEmployeeLastName()))
                        .thenReturn(employeeModelsMock);
        List<EmployeeModel> employeeModelsReturned = employeeService.searchEmployeesBy(params);

        assertEquals(employeeModelsMock.size(), employeeModelsReturned.size());

        for (int i = 0; i < employeeModelsReturned.size(); i++) {
            assertEquals(employeeModelsMock.get(i), employeeModelsReturned.get(i));
        }
    }

    @Test
    void test_employeeService_searchEmployeesBy_fName_lName() {
        Map<String, String> params = Map.of("fName", employeeModel.getEmployeeFirstName(), "lName",
                employeeModel.getEmployeeLastName());
        List<EmployeeModel> employeeModelsMock = new LinkedList<EmployeeModel>(
                Arrays.asList(employeeModel, employeeModel, employeeModel));
        when(employeeRespository
                .findByEmployeeFirstNameContaining(employeeModel.getEmployeeFirstName()))
                        .thenReturn(employeeModelsMock);
        when(employeeRespository
                .findByEmployeeLastNameContaining(employeeModel.getEmployeeLastName()))
                        .thenReturn(employeeModelsMock);
        List<EmployeeModel> employeeModelsReturned = employeeService.searchEmployeesBy(params);

        assertEquals(employeeModelsMock.size(), employeeModelsReturned.size());

        for (int i = 0; i < employeeModelsReturned.size(); i++) {
            assertEquals(employeeModelsMock.get(i), employeeModelsReturned.get(i));
        }
    }

    @Test
    void test_employeeService_searchEmployeesBy_empty() {
        Map<String, String> params = Collections.emptyMap();
        List<EmployeeModel> employeeModelsMock = Collections.emptyList();
        List<EmployeeModel> employeeModelsReturned = employeeService.searchEmployeesBy(params);
        assertEquals(employeeModelsMock, employeeModelsReturned);
    }

    @Test
    void test_employeeService_getEmployeeByEmployeeWorkDepartment() {
        List<EmployeeModel> employeeModelsMock =
                Arrays.asList(employeeModel, employeeModel, employeeModel);
        when(employeeRespository.findByEmployeeWorkDepartment(anyString()))
                .thenReturn(employeeModelsMock);
        List<EmployeeModel> employeeModelsReturned =
                employeeService.getEmployeeByEmployeeWorkDepartment(anyString());

        assertEquals(employeeModelsMock.size(), employeeModelsReturned.size());

        for (int i = 0; i < employeeModelsReturned.size(); i++) {
            assertEquals(employeeModelsMock.get(i), employeeModelsReturned.get(i));
        }
    }
}
