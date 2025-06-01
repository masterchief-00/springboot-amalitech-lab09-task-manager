package com.kwizera.springbootamalitechlab09taskmanager.services.impl;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.EmployeeDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Employee;
import com.kwizera.springbootamalitechlab09taskmanager.services.UserServices;
import com.kwizera.springbootamalitechlab09taskmanager.utils.InputValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    private final EmployeeDAO employeeDAO;

    public UserServicesImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee signIn(Employee employee) throws InvalidInputException {
        if (InputValidationUtil.invalidNames(employee.getFirstName()) || InputValidationUtil.invalidNames(employee.getLastName())) {
            throw new InvalidInputException("Invalid names");
        }
        if (InputValidationUtil.invalidEmail(employee.getEmail())) {
            throw new InvalidInputException("Invalid email");
        }
        return employeeDAO.register(employee);
    }
}
