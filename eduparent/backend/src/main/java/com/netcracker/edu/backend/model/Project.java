package com.netcracker.edu.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Project {
    private int id;
    private String code;
    private String description;
    private int createdId;
    private Account accountByCreatedId;
    @JsonIgnore
    private Collection<Task> tasksById;

    public Project(int id,String code,String description){
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Project() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created_id", nullable = true,insertable = false,updatable = false)
    public int getCreatedId() {
        return createdId;
    }

    public void setCreatedId(int createdId) {
        this.createdId = createdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                createdId == project.createdId &&
                Objects.equals(code, project.code) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, createdId);
    }

    @ManyToOne
    @JoinColumn(name = "created_id", referencedColumnName = "id", nullable = false)
    public Account getAccountByCreatedId() {
        return accountByCreatedId;
    }

    public void setAccountByCreatedId(Account accountByCreatedId) {
        this.accountByCreatedId = accountByCreatedId;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<Task> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<Task> tasksById) {
        this.tasksById = tasksById;
    }
}
