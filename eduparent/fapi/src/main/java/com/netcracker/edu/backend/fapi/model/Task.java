package com.netcracker.edu.backend.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private long id;
    @NotNull
    @Size(max=45)
    private String code;
    @NotNull
    @DateTimeFormat
    private Date created;
    @DateTimeFormat
    private Date closed;
    @NotNull
    @DateTimeFormat
    private Date updated;
    @NotNull
    @DateTimeFormat
    private Date dueDate;
    @NotNull
    @Digits(integer=3, fraction=0)
    private Integer estimation;
    @NotNull
    @Size(max=45)
    private String description;
    @NotNull
    private Account reporter;
    @NotNull
    private long priorityId;
    private Priority priority;
    @NotNull
    private long statusId;
    private Status status;
    @NotNull
    private long assignedId;
    private Account assignee;
    @NotNull
    private Long projectId;

    public Task(){}

    public Task(long id, String code, Date created, Date closed, Date updated, Date dueDate,
                Integer estimation, String description, Account assignee, Account reporter,
                Status status, Priority priority, Long projectId, Long priorityId,Long assignedId,Long statusId) {
        this.id = id;
        this.code = code;
        this.created = created;
        this.closed = closed;
        this.updated = updated;
        this.dueDate = dueDate;
        this.estimation = estimation;
        this.description = description;
        this.assignee = assignee;
        this.reporter = reporter;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.priorityId = priorityId;
        this.assignedId = assignedId;
        this.statusId = statusId;
    }

    public long getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(long assignedId) {
        this.assignedId = assignedId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAssignee() {
        return assignee;
    }

    public void setAssignee(Account assignee) {
        this.assignee = assignee;
    }

    public Account getReporter() {
        return reporter;
    }

    public void setReporter(Account reporter) {
        this.reporter = reporter;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
