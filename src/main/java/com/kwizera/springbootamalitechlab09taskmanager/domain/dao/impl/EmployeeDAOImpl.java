package com.kwizera.springbootamalitechlab09taskmanager.domain.dao.impl;

import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.EmployeeDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    private final HashMap<UUID, Employee> employeeHashMap = new HashMap<>();

    @Override
    public Employee findById(UUID id) {
        return employeeHashMap.get(id);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeHashMap
                .values()
                .stream()
                .filter(e -> e.getEmail().toLowerCase().equals(email))
                .toList()
                .get(0);
    }

    @Override
    public Employee register(Employee employee) {
        UUID employeeId = UUID.randomUUID();
        employee.setId(employeeId);
        employeeHashMap.put(employeeId, employee);
        return employee;
    }

    @Override
    public void delete(UUID id) {
        employeeHashMap.remove(id);
    }
}
