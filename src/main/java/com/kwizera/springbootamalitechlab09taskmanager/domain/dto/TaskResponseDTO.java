package com.kwizera.springbootamalitechlab09taskmanager.domain.dto;

import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;

import java.time.LocalDate;

public class TaskResponseDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private String owner;

    public TaskResponseDTO(String title, String description, LocalDate dueDate, TaskPriority priority, String owner) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.owner = owner;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
