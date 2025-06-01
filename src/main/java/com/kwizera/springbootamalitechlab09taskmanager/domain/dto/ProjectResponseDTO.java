package com.kwizera.springbootamalitechlab09taskmanager.domain.dto;

import java.time.LocalDate;

public class ProjectResponseDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private String owner;

    public ProjectResponseDTO(String title, String description, LocalDate dueDate, String owner) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
