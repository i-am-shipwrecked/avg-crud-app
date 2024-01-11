package com.example.FinalProjectApplication.controllers;

import com.example.FinalProjectApplication.ResourceNotFoundException;
import com.example.FinalProjectApplication.tables.Projects;
import com.example.FinalProjectApplication.tables.Tasks;
import com.example.FinalProjectApplication.repositories.ProjectRepository;
import com.example.FinalProjectApplication.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TasksController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/api/projects/getTasksByProjectId/{id}")
    public List<Tasks> getTasksByProjectId(@PathVariable("id") UUID projectId) {
        Optional<Projects> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            return project.get().getTasksList();
        } else {
            return Collections.emptyList();
        }
    }
    @PostMapping("/api/projects/{id}/tasks")
//    {
//        "name": "Название задачи",
//            "description": "Описание задачи",
//            "deadline": "Крайний срок",
//            "status": "in progress",
//            "project": {
//        "id": "46d0529f-5622-4944-bef0-a32b5ecefe54"
//    },
//        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
//    }
    public ResponseEntity<Tasks> createTask(@PathVariable("id") UUID id, @RequestBody Tasks task) {
        Optional<Projects> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Projects project = projectOptional.get();
            task.setProject(project);
            if (task.getStatus().equalsIgnoreCase("in progress")) {
                task.setStatus("in progress");
            } else if (task.getStatus().equalsIgnoreCase("done")) {
                task.setStatus("done");
            } else {
                return ResponseEntity.badRequest().build();
            }
            Tasks createdTask = taskRepository.save(task);
            project.getTasksList().add(createdTask);
            projectRepository.save(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/projects/{id}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") UUID id, @PathVariable("taskId") UUID taskId) {
        Optional<Projects> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Projects project = projectOptional.get();
            Optional<Tasks> taskOptional = taskRepository.findById(taskId);
            if (taskOptional.isPresent()) {
                Tasks task = taskOptional.get();
                if (project.getTasksList().contains(task)) {
                    project.getTasksList().remove(task);
                    projectRepository.save(project);
                    taskRepository.delete(task);
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/projects/{projectId}/tasks/{taskId}")
    public Tasks getTaskById(@PathVariable("projectId") UUID projectId, @PathVariable("taskId") UUID taskId) {
        Optional<Projects> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Projects project = projectOptional.get();
            Optional<Tasks> taskOptional = taskRepository.findById(taskId);
            if (taskOptional.isPresent()) {
                Tasks task = taskOptional.get();
                if (task.getProject().equals(project)) {
                    return task;
                } else {
                    throw new ResourceNotFoundException("Task not found");
                }
            } else {
                throw new ResourceNotFoundException("Task not found");
            }
        } else {
            throw new ResourceNotFoundException("Project not found");
        }
    }

    @PutMapping("/api/projects/{projectId}/tasks/{taskId}")
    public Tasks updateTask(@PathVariable("id") UUID projectId, @PathVariable("taskId") UUID taskId, @RequestBody Tasks updatedTask) {
        Optional<Projects> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Projects project = projectOptional.get();
            Optional<Tasks> taskOptional = taskRepository.findById(taskId);
            if (taskOptional.isPresent()) {
                Tasks task = taskOptional.get();
                if (task.getProject().equals(project)) {
                    task.setName(updatedTask.getName());
                    task.setDescription(updatedTask.getDescription());
                    return taskRepository.save(task);
                }
            }
        }
        return updatedTask;
    }
}