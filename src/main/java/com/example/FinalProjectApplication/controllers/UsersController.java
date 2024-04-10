package com.example.FinalProjectApplication.controllers;

import com.example.FinalProjectApplication.ResourceNotFoundException;
import com.example.FinalProjectApplication.services.UserService;
import com.example.FinalProjectApplication.tables.Users;
import com.example.FinalProjectApplication.repositories.ProjectRepository;
import com.example.FinalProjectApplication.repositories.TaskRepository;
import com.example.FinalProjectApplication.repositories.UsersRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "The User API")
public class UsersController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserService userService;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("/api/register")
    @Operation(summary = "Register a new user")
    public Users registerUser(@RequestBody Users user) {
        String password = user.getPassword();
        if (password != null && !password.isEmpty()) {
            return usersRepository.save(user);
        } else {
            throw new IllegalArgumentException("Password is required");
        }
    }

    @GetMapping("/api/user/{id}")
    @Operation(summary = "Get user by ID")
    public Users getUserProfile(@PathVariable("id") UUID id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @GetMapping("/api/user/profile")
    @Operation(summary = "Get user by username and password")
    public Users getUserByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    @PutMapping("/api/user/{id}/profile")
    @Operation(summary = "Update user's username by ID")
    public Users updateProfile(@PathVariable("id") UUID id, @RequestBody Users updatedUser) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users existingUser = userOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            return usersRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @JsonIgnore
    @PutMapping("/api/user/password/{id}")
    @Operation(summary = "Update users password by ID")
    public Users updatePassword(@PathVariable("id") UUID id, @RequestBody String newPassword) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users existingUser = userOptional.get();
            existingUser.setPassword(newPassword);
            return usersRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @DeleteMapping("/api/user/{id}")
    @Operation(summary = "Delete user by ID")
    public void deleteUserProfile(@PathVariable("id") UUID id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
