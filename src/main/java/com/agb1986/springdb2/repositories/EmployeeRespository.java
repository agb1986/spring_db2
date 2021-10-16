package com.agb1986.springdb2.repositories;

import java.util.Optional;

import com.agb1986.springdb2.models.EmployeeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRespository extends JpaRepository<EmployeeModel, Long> {
    Optional<EmployeeModel> findByEmployeeNumber(String employeeNumber);
}