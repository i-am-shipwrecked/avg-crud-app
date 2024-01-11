package com.example.FinalProjectApplication.Tables;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;


@Table(name = "PROJECTS")
@Entity
public class Projects {
    @Column
    @GeneratedValue
    @Id
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String beginning;
    @Column
    private String ending;
    @Column
    @OneToMany
    @JsonIgnore
    List<Tasks>tasksList;
    @Column
    @OneToMany
    @JsonIgnore
    List<Users>users;

    public Projects(UUID id, String name, String description, String beginning, String ending, List<Tasks> tasksList, List<Users> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.beginning = beginning;
        this.ending = ending;
        this.tasksList = tasksList;
        this.users = users;
    }

    public Projects() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }


    public void addTask(Tasks savedTask) {
        tasksList.add(savedTask);
    }
}
