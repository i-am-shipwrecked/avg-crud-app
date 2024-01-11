package com.example.FinalProjectApplication.controllers;

import com.example.FinalProjectApplication.tables.Projects;
import com.example.FinalProjectApplication.tables.Tasks;
import com.example.FinalProjectApplication.tables.Users;
import com.example.FinalProjectApplication.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
//@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    ProjectRepository projectRepository;

    public ProjectsController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping("/api/projects/createProject")
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

    @GetMapping("api/projects/getAllProjects")
    public List<Projects> getAllProjects() {
        return (List<Projects>) projectRepository.findAll();
    }

    @PutMapping("/api/projects/updateProjectById/{id}")
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
    public void addTask(Tasks task) {

    }



}
