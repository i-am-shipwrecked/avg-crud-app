package com.example.FinalProjectApplication.Tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Entity
@Table(name = "USERS")
public class Users {
    @Column
    @jakarta.persistence.Id
    @GeneratedValue
    private UUID userId;
    @Column
    private String username;

    @Column
    private String password;

    public Users(UUID userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
