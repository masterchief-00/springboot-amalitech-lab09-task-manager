package com.kwizera.springbootamalitechlab09taskmanager.domain.dao;

import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectDAO {
    Project findById(UUID id);

    List<Project> findAll(String email);

    Project createProject(Project project);

    Project update(UUID projectId, String field, Object newValue);

    void delete(UUID id);
}
