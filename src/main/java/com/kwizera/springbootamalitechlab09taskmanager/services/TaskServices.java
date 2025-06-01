package com.kwizera.springbootamalitechlab09taskmanager.services;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.ProjectNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskServices {
    Task addTask(UUID projectId, Task task) throws InvalidInputException, ProjectNotFoundException;

    List<Task> getTasks(UUID projectId);

    Task getTask(UUID taskId);
}
