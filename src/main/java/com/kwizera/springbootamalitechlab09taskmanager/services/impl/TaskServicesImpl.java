package com.kwizera.springbootamalitechlab09taskmanager.services.impl;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.ProjectNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.ProjectDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.TaskDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskStatus;
import com.kwizera.springbootamalitechlab09taskmanager.services.TaskServices;
import com.kwizera.springbootamalitechlab09taskmanager.utils.InputValidationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public List<Task> getTasks(UUID projectId) {
        return taskDAO.findAll(projectId);
    }

    @Override
    public Task getTask(UUID taskId) {
        return taskDAO.findById(taskId);
    }

    @Override
    public Task updateTask(UUID taskId, String field, Object newValue) throws InvalidInputException {
        switch (field) {
            case "title" -> {
                if (InputValidationUtil.invalidProjectTitle((String) newValue))
                    throw new InvalidInputException("Invalid title");
            }
            case "description" -> {
                if (InputValidationUtil.invalidProjectDescription((String) newValue))
                    throw new InvalidInputException("Invalid description");
            }
            case "dueDate" -> {
                if (InputValidationUtil.invalidLocalDate((String) newValue))
                    throw new InvalidInputException("Invalid date");
            }
            case "priority" -> {
                if (!((String) newValue).equals("HIGH")
                        && !((String) newValue).equals("MEDIUM")
                        && !((String) newValue).equals("LOW")) {
                    throw new InvalidInputException("Invalid input for priority");
                }
            }
            case "status" -> {
                if (!((String) newValue).equals("COMPLETED")
                        && !((String) newValue).equals("PENDING")
                        && !((String) newValue).equals("CANCELLED")) {
                    throw new InvalidInputException("Invalid input for status");
                }
            }
            default -> {
                throw new InvalidInputException("Invalid inputs");
            }
        }

        return taskDAO.update(taskId, field, newValue);
    }

    @Override
    public void deleteTask(UUID id) {
        taskDAO.delete(id);
    }
}
