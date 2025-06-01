package com.kwizera.springbootamalitechlab09taskmanager.domain.dao.impl;

import com.kwizera.springbootamalitechlab09taskmanager.domain.dao.ProjectDAO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
    private final HashMap<UUID, Project> projectHashMap = new HashMap<>();

    @Override
    public Project findById(UUID id) {
        return projectHashMap.get(id);
    }

    @Override
    public Project createProject(Project project) {
        UUID projectId = UUID.randomUUID();
        project.setId(projectId);
        projectHashMap.put(projectId, project);
        return project;
    }

    @Override
    public Project update(UUID projectId, String field, Object newValue) {
        Project project = projectHashMap.get(projectId);
        if (project == null) {
            return null;
        }

        switch (field) {
            case "title" -> {
                if (newValue instanceof String) project.setTitle((String) newValue);
            }
            case "description" -> {
                if (newValue instanceof String) project.setDescription((String) newValue);
            }
            case "dueDate" -> {
                if (newValue instanceof LocalDate) project.setDueDate((LocalDate) newValue);
            }
            default -> {
                return null;
            }
        }
        projectHashMap.put(projectId, project);
        return project;
    }

    @Override
    public void delete(UUID id) {
        projectHashMap.remove(id);
    }
}
