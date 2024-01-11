package com.example.FinalProjectApplication.Tables;



import jakarta.persistence.*;


import java.util.UUID;

@Table(name = "TASKS")
@Entity
public class Tasks {
    @jakarta.persistence.Id
    @Column
    @GeneratedValue
    private UUID Id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String deadline;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn
    private Projects project;

    public Tasks(String name, String description, String deadline, String status, Projects project) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tasks() {
    }


    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


}
