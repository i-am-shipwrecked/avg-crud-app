package com.example.FinalProjectApplication.controllers;

import com.example.FinalProjectApplication.ResourceNotFoundException;
import com.example.FinalProjectApplication.Tables.Users;
import com.example.FinalProjectApplication.repositories.ProjectRepository;
import com.example.FinalProjectApplication.repositories.TaskRepository;
import com.example.FinalProjectApplication.repositories.UsersRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UsersController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("/api/register")
    public Users registerUser(@RequestBody Users user) {
        String password = user.getPassword();
        if (password != null && !password.isEmpty()) {
            return usersRepository.save(user);
        } else {
            throw new IllegalArgumentException("Password is required");
        }
    }

    @GetMapping("/api/user/{id}")
    public Users getUserProfile(@PathVariable("id") UUID id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }



    @PutMapping("/api/user/{id}/profile")
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
    public void deleteUserProfile(@PathVariable("id") UUID id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }


}
