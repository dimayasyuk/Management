package com.netcracker.edu.backend.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task {
    private long id;
    private String code;
    private Date created;
    private Date closed;
    private Date updated;
    private Date dueDate;
    private Integer estimation;
    private String description;
    private Account assignee;
    private Account reporter;
    private Status status;
    private long priorityId;
    private long statusId;
    private long assignedId;
    private Priority priority;
    private Long projectId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic
    @Column(name = "closed")
    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    @Basic
    @Column(name = "updated")
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "due_date")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "estimation")
    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "priority_id")
    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    @Basic
    @Column(name = "status_id")
    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "assigned_id")
    public long getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(long assignedId) {
        this.assignedId = assignedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(code, task.code) &&
                Objects.equals(created, task.created) &&
                Objects.equals(updated, task.updated) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(estimation, task.estimation) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, created, updated, dueDate, estimation, description);
    }

    @ManyToOne
    @JoinColumn(name = "assigned_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Account getAssignee() {
        return assignee;
    }

    public void setAssignee(Account assignee) {
        this.assignee = assignee;
    }

    @ManyToOne
    @JoinColumn(name = "created_id", referencedColumnName = "id")
    public Account getReporter() {
        return reporter;
    }

    public void setReporter(Account reporter) {
        this.reporter = reporter;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
