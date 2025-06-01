package com.kwizera.springbootamalitechlab09taskmanager.services.impl;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.UserNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.EmployeeDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.ProjectDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Employee;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;
import com.kwizera.springbootamalitechlab09taskmanager.services.ProjectServices;
import com.kwizera.springbootamalitechlab09taskmanager.utils.InputValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@Service
public class ProjectServicesImpl implements ProjectServices {
    private final ProjectDAO projectDAO;
    private final EmployeeDAO employeeDAO;

    public ProjectServicesImpl(ProjectDAO projectDAO, EmployeeDAO employeeDAO) {
        this.projectDAO = projectDAO;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Project addProject(String email, Project project) throws InvalidInputException, UserNotFoundException, IllegalArgumentException {
        if (InputValidationUtil.invalidProjectDescription(project.getDescription())) {
            throw new InvalidInputException("Invalid description");
        } else if (InputValidationUtil.invalidProjectTitle(project.getTitle())) {
            throw new InvalidInputException("Invalid title");
        } else if (InputValidationUtil.invalidLocalDate(project.getDueDate().toString())) {
            throw new InvalidInputException("Invalid due date");
        } else if (email.isEmpty()) {
            throw new IllegalArgumentException("No user selected");
        } else {
            Employee employee = employeeDAO.findByEmail(email);
            if (employee == null) {
                throw new UserNotFoundException("User of email " + email + " not found");
            }

            project.setEmployee(employee);

            return projectDAO.createProject(project);
        }
    }

    @Override
    public List<Project> getProjects(String userEmail) {
        return projectDAO.findAll(userEmail);
    }

    @Override
    public Project getProject(UUID id) {
        return projectDAO.findById(id);
    }

    @Override
    public Project updateProject(UUID id, String field, Object newValue) {
        return projectDAO.update(id, field, newValue);
    }
}
