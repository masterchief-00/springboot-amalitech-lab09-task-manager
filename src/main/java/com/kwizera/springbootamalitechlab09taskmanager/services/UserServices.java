package com.kwizera.springbootamalitechlab09taskmanager.services;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Employee;

public interface UserServices {
    Employee signIn(Employee employee) throws InvalidInputException;
}
