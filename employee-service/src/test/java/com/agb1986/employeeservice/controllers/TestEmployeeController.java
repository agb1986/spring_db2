package com.agb1986.employeeservice.controllers;

import com.agb1986.employeeservice.models.EmployeeModel;
import com.agb1986.employeeservice.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebMvcTest(EmployeeController.class)
class TestEmployeeController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    private static EmployeeModel employeeModel;

    private static final String EXPECTED_EMPLOYEE_MODEL_RESPONSE_OBJECT =
            "{\"employeeNumber\":\"000010\",\"employeeFirstName\":\"CHRISTINE\",\"employeeMiddleInital\":\"I\",\"employeeLastName\":\"HAAS\",\"employeeWorkDepartment\":\"A00\",\"employeePhoneNumber\":\"3978\"}";
    private static final String EXPECTED_EMPLOYEE_MODEL_RESPONSE_LIST =
            "[{\"employeeNumber\":\"000010\",\"employeeFirstName\":\"CHRISTINE\",\"employeeMiddleInital\":\"I\",\"employeeLastName\":\"HAAS\",\"employeeWorkDepartment\":\"A00\",\"employeePhoneNumber\":\"3978\"}]";

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
    void test_employeeController_getAllEmployees() throws Exception {
        List<EmployeeModel> employeeModels = Arrays.asList(employeeModel);
        when(employeeService.getAllEmployees()).thenReturn(employeeModels);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/employee").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(EXPECTED_EMPLOYEE_MODEL_RESPONSE_LIST));
    }

    @Test
    void test_employeeController_getEmployeeByEmployeeNumber() throws Exception {
        String employeeNumber = employeeModel.getEmployeeNumber();
        when(employeeService.getEmployeeByEmployeeNumber(employeeNumber)).thenReturn(employeeModel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/" + employeeNumber)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(EXPECTED_EMPLOYEE_MODEL_RESPONSE_OBJECT));
    }

    @Test
    void test_employeeController_getEmployeeByEmployeeNumber_404() throws Exception {
        String employeeNumber = "abc123";
        when(employeeService.getEmployeeByEmployeeNumber(employeeNumber)).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/" + employeeNumber)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isNotFound(),
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test_employeeController_getEmployeeSearch() throws Exception {
        List<EmployeeModel> employeeModels = Arrays.asList(employeeModel);
        Map<String, String> params = Map.of("fName", employeeModel.getEmployeeFirstName(), "lName",
                employeeModel.getEmployeeLastName());
        when(employeeService.searchEmployeesBy(params)).thenReturn(employeeModels);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/search")
                .param("fName", employeeModel.getEmployeeFirstName())
                .param("lName", employeeModel.getEmployeeLastName())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(EXPECTED_EMPLOYEE_MODEL_RESPONSE_LIST));
    }

    @Test
    void test_employeeController_getEmployeeSearch_400() throws Exception {
        when(employeeService.searchEmployeesBy(null)).thenReturn(Collections.emptyList());

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/employee/search").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isBadRequest(),
                content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void test_employeeController_getEmployeeByEmployeeWorkDepartment() throws Exception {
        String employeeWorkDepartment = employeeModel.getEmployeeWorkDepartment();
        List<EmployeeModel> employeeModels = Arrays.asList(employeeModel);
        when(employeeService.getEmployeeByEmployeeWorkDepartment(employeeWorkDepartment))
                .thenReturn(employeeModels);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/employee/deparment/" + employeeWorkDepartment)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(EXPECTED_EMPLOYEE_MODEL_RESPONSE_LIST));
    }

    @Test
    void test_employeeController_getEmployeeByEmployeeWorkDepartment_404() throws Exception {
        String employeeWorkDepartment = employeeModel.getEmployeeWorkDepartment();
        when(employeeService.getEmployeeByEmployeeWorkDepartment(employeeWorkDepartment))
                .thenReturn(Collections.emptyList());

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/employee/deparment/" + employeeWorkDepartment)
                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpectAll(status().isNotFound(),
                content().contentType(MediaType.APPLICATION_JSON));
    }
}
