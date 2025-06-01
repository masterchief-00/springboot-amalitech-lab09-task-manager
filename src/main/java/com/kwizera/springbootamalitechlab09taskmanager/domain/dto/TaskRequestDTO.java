package com.kwizera.springbootamalitechlab09taskmanager.domain.dto;

import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;

import java.time.LocalDate;

public class TaskRequestDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String taskPriority;

    public TaskRequestDTO(String title, String description, LocalDate dueDate, String taskPriority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.taskPriority = taskPriority;
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

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }
}
