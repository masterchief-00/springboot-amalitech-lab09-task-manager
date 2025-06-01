package com.kwizera.springbootamalitechlab09taskmanager.services.impl;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.ProjectNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.ProjectDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.TaskDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;
import com.kwizera.springbootamalitechlab09taskmanager.services.TaskServices;
import com.kwizera.springbootamalitechlab09taskmanager.utils.InputValidationUtil;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class TaskServicesImpl implements TaskServices {
    private final TaskDAO taskDAO;
    private final ProjectDAO projectDAO;

    public TaskServicesImpl(TaskDAO taskDAO, ProjectDAO projectDAO) {
        this.taskDAO = taskDAO;
        this.projectDAO = projectDAO;
    }

    @Override
    public Task addTask(UUID projectId, Task task) throws InvalidInputException, ProjectNotFoundException {
        if (InputValidationUtil.invalidProjectDescription(task.getDescription())) {
            throw new InvalidInputException("Invalid description");
        } else if (InputValidationUtil.invalidProjectTitle(task.getTitle())) {
            throw new InvalidInputException("Invalid title");
        } else if (InputValidationUtil.invalidLocalDate(task.getDueDate().toString())) {
            throw new InvalidInputException("Invalid due date");
        } else if (projectId.toString().isEmpty()) {
            throw new IllegalArgumentException("No projected selected");
        } else {
            Project project = projectDAO.findById(projectId);
            if (project == null) {
                throw new ProjectNotFoundException("No project selected");
            }
            task.setProject(project);
            return taskDAO.createTask(task);
        }
    }
}
