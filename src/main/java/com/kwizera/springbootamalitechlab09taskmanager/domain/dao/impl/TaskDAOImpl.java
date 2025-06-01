package com.kwizera.springbootamalitechlab09taskmanager.domain.dao.impl;

import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.TaskDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private final HashMap<UUID, Task> taskHashMap = new HashMap<>();

    @Override
    public Task findById(UUID id) {
        return taskHashMap.get(id);
    }

    @Override
    public List<Task> findAll(UUID projectId) {
        return taskHashMap.values().stream().filter(t -> t.getProject().getId().equals(projectId)).toList();
    }

    @Override
    public Task createTask(Task task) {
        UUID taskId = UUID.randomUUID();
        task.setId(taskId);
        taskHashMap.put(taskId, task);
        return task;
    }

    @Override
    public Task update(UUID taskId, String field, Object newValue) {
        Task task = taskHashMap.get(taskId);

        if (task == null) {
            return null;
        }

        switch (field) {
            case "title" -> {
                if (newValue instanceof String) task.setTitle((String) newValue);
            }
            case "description" -> {
                if (newValue instanceof String) task.setDescription((String) newValue);
            }
            case "dueDate" -> {
                if (newValue instanceof LocalDate) task.setDueDate((LocalDate) newValue);
            }
            case "priority" -> {
                if (newValue instanceof TaskPriority) task.setTaskPriority((TaskPriority) newValue);
            }
            case "status" -> {
                if (newValue instanceof TaskStatus) task.setTaskStatus((TaskStatus) newValue);
            }
            default -> {
                return null;
            }
        }
        taskHashMap.put(taskId, task);
        return task;
    }

    @Override
    public void delete(UUID id) {
        taskHashMap.remove(id);
    }
}
