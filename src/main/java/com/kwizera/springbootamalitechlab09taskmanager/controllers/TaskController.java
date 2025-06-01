package com.kwizera.springbootamalitechlab09taskmanager.controllers;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.ProjectNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.TaskRequestDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.TaskResponseDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;
import com.kwizera.springbootamalitechlab09taskmanager.services.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/{user_email}/project/{project_id}/tasks")
public class TaskController {
    private final TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID task_id) {
        Task task = taskServices.getTask(task_id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable UUID project_id, @PathVariable String user_email) {
        List<Task> taskList = taskServices.getTasks(project_id);

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createNewTask(@PathVariable UUID project_id, @RequestBody TaskRequestDTO taskDetails, @PathVariable String user_email) throws InvalidInputException, ProjectNotFoundException {
        TaskPriority priority = switch (taskDetails.getTaskPriority()) {
            case "HIGH" -> TaskPriority.HIGH;
            case "MEDIUM" -> TaskPriority.MEDIUM;
            case "LOW" -> TaskPriority.LOW;
            default -> null;
        };

        if (priority == null) {
            throw new InvalidInputException("Invalid task priority value");
        }
        Task task = taskServices.addTask(project_id, new Task(taskDetails.getTitle(), taskDetails.getDescription(), taskDetails.getDueDate(), priority));
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO(task.getTitle(), task.getDescription(), task.getDueDate(), task.getTaskPriority(), task.getProject().getTitle());

        return new ResponseEntity<>(taskResponseDTO, HttpStatus.CREATED);
    }
}
