package com.kwizera.springbootamalitechlab09taskmanager.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Project {
    private UUID id;
    private Employee employee;
    private String title;
    private String description;
    private LocalDate dueDate;
    private LocalDate createdAt;

    public Project(UUID id, Employee employee, String title, String description, LocalDate dueDate, LocalDate createdAt) {
        this.id = id;
        this.employee = employee;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
