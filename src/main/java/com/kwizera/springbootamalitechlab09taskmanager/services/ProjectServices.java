package com.kwizera.springbootamalitechlab09taskmanager.services;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.UserNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;

public interface ProjectServices {
    Project addProject(String userEmail, Project project) throws InvalidInputException, UserNotFoundException, IllegalArgumentException;
}
