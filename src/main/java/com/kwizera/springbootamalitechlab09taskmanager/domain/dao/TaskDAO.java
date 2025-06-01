package com.kwizera.springbootamalitechlab09taskmanager.domain.dao;

import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;

import java.util.UUID;

public interface TaskDAO {
    Task findById(UUID id);

    Task createTask(Task task);

    Task update(UUID taskId, String field, Object newValue);

    void delete(UUID id);
}
