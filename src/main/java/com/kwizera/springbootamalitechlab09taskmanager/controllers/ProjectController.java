package com.kwizera.springbootamalitechlab09taskmanager.controllers;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.UserNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.ProjectRequestDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.ProjectResponseDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.ProjectUpdateRequestDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Project;
import com.kwizera.springbootamalitechlab09taskmanager.services.ProjectServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/{user_email}/projects")
@Tag(name = "Projects Controller", description = "This controller exposes endpoints for all CRUD operations regarding projects")
public class ProjectController {
    private final ProjectServices projectServices;

    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }

    @Operation(summary = "Retrieves all projects of a signed in user")
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(@PathVariable String user_email) {
        List<Project> projectList = projectServices.getProjects(user_email);
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @Operation(summary = "Gets a single project")
    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getSingleProject(@PathVariable UUID project_id, @PathVariable String user_email) {
        Project project = projectServices.getProject(project_id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @Operation(summary = "Creates a new project")
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createNewProject(@PathVariable String user_email, @RequestBody ProjectRequestDTO projectInfo) throws UserNotFoundException, InvalidInputException {
        Project project = projectServices.addProject(user_email, new Project(projectInfo.getTitle(), projectInfo.getDescription(), projectInfo.getDueDate()));
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(
                project.getTitle(),
                project.getDescription(),
                project.getDueDate(),
                project.getEmployee().getLastName() + " " + project.getEmployee().getFirstName()
        );

        return new ResponseEntity<>(projectResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Updates an existing project")
    @PatchMapping("/{project_id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable UUID project_id, @RequestBody ProjectUpdateRequestDTO updateDetails) throws InvalidInputException {
        Project project = projectServices.updateProject(project_id, updateDetails.getField(), updateDetails.getNewValue());
        if (project == null)
            throw new RuntimeException("Project not updated");
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(project.getTitle(), project.getDescription(), project.getDueDate(), project.getEmployee().getLastName() + " " + project.getEmployee().getFirstName());
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Deletes a project by uuid")
    @DeleteMapping("/{project_id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID project_id) {
        projectServices.deleteProject(project_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
