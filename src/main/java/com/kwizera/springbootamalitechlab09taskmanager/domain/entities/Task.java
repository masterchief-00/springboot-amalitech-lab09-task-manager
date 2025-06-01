package com.kwizera.springbootamalitechlab09taskmanager.domain.entities;

import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskStatus;

import java.util.UUID;

public class Task {
    private UUID id;
    private Project project;
    private String title;
    private String description;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;

    public Task(UUID id, Project project, String title, String description, TaskPriority taskPriority, TaskStatus taskStatus) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.description = description;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
    }

    public Task(UUID id, Project project, String title, String description, TaskPriority taskPriority) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.description = description;
        this.taskPriority = taskPriority;
        this.taskStatus = TaskStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
