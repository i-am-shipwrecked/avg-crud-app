package com.example.FinalProjectApplication.controllers;

import com.example.FinalProjectApplication.repositories.TaskRepository;
import com.example.FinalProjectApplication.tables.Projects;
import com.example.FinalProjectApplication.tables.Tasks;
import com.example.FinalProjectApplication.tables.Users;
import com.example.FinalProjectApplication.repositories.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@Tag(name = "Project", description = "The Project API")
public class ProjectsController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskRepository taskRepository;

    public ProjectsController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping("/api/createProject")
    //добавить чтобы он возвращал uid созданного проекта
    @Operation(summary = "Create a new project")
    public void createProject(@RequestBody Projects project) {
        Projects newProject = new Projects(project.getId(), project.getName(), project.getDescription(), project.getBeginning(), project.getEnding(), project.getTasksList(), project.getUsers());
        if (project.getTasksList() != null) {
            for (Tasks task : project.getTasksList()) {
                newProject.getTasksList().add(task);
            }
        }
        if (project.getUsers() != null) {
            for (Users user : project.getUsers()) {
                newProject.getUsers().add(user);
            }
        }
        projectRepository.save(newProject);
    }

    @GetMapping("api/getAllProjects")
    @Operation(summary = "Get all projects")
    public List<Projects> getAllProjects() {
        return (List<Projects>) projectRepository.findAll();
    }

    @PutMapping("/api/projects/updateProjectById/{id}")
    @Operation(summary = "Update project by ID")
    public void updateProjectById(@PathVariable UUID id, @RequestBody Projects project) {
        Projects existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setBeginning(project.getBeginning());
            existingProject.setEnding(project.getEnding());
            existingProject.setTasksList(project.getTasksList());
            existingProject.setUsers(project.getUsers());
            projectRepository.save(existingProject);
        }
    }

    @DeleteMapping("/api/projects/deleteProjectById/{id}")
    @Operation(summary = "Delete project by ID")
    public void deleteProjectById(@PathVariable UUID id) {
        projectRepository.deleteById(id);
    }

    @GetMapping("/api/projects/getProjectById/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable("id") UUID id) {
        Optional<Projects> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Projects project = optionalProject.get();
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/projects/addTaskToProject/{projectId}")
    @Operation(summary = "Add task to the project by project ID")
    public void addTaskToProject(@PathVariable UUID projectId, @RequestBody Tasks task) {
        Projects project = projectRepository.findById(projectId).orElse(null);

        if (project != null) {
            Tasks existingTask = taskRepository.findById(task.getId()).orElse(null);

            if (existingTask == null) {
                task.setProject(project);
                project.getTasksList().add(task);
                projectRepository.save(project);
            } else {
                existingTask.setName(task.getName());
                existingTask.setDescription(task.getDescription());
                existingTask.setDeadline(task.getDeadline());
                existingTask.setStatus(task.getStatus());
                existingTask.setProject(project);

                taskRepository.save(existingTask);
            }
        }
    }
}
