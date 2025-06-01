package com.kwizera.springbootamalitechlab09taskmanager.domain.dao;

import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Employee;

import java.util.UUID;

public interface EmployeeDAO {
    Employee findById(UUID id);

    Employee findByEmail(String email);

    Employee register(Employee employee);

    void delete(UUID id);
}
