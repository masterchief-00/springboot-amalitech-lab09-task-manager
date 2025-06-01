package com.kwizera.springbootamalitechlab09taskmanager.controllers;

import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.InvalidInputException;
import com.kwizera.springbootamalitechlab09taskmanager.Exceptions.ProjectNotFoundException;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.TaskRequestDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.TaskResponseDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.dto.TaskUpdateRequestDTO;
import com.kwizera.springbootamalitechlab09taskmanager.domain.entities.Task;
import com.kwizera.springbootamalitechlab09taskmanager.domain.enums.TaskPriority;
import com.kwizera.springbootamalitechlab09taskmanager.services.TaskServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/{user_email}/project/{project_id}/tasks")
@Tag(name = "Task Controller", description = "This controller exposes endpoints for all CRUD operations regarding tasks")
public class TaskController {
    private final TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @Operation(summary = "Gets a single task by uuid")
    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID task_id) {
        Task task = taskServices.getTask(task_id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(summary = "Retrieves all tasks of a project")
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable UUID project_id, @PathVariable String user_email) {
        List<Task> taskList = taskServices.getTasks(project_id);

        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @Operation(summary = "Creates a new task in a project")
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

    @Operation(summary = "Updates an existing task")
    @PatchMapping("/{task_id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID task_id, @RequestBody TaskUpdateRequestDTO updateDetails) throws InvalidInputException {
        Task task = taskServices.updateTask(task_id, updateDetails.getField(), updateDetails.getNewValue());
        if (task == null) throw new RuntimeException("Task not updated");

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO(task.getTitle(), task.getDescription(), task.getDueDate(), task.getTaskPriority(), task.getProject().getTitle());
        return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Deletes a task")
    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID task_id) {
        taskServices.deleteTask(task_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
