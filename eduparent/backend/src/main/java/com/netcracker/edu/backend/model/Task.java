package com.netcracker.edu.backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task {
    private int id;
    private int projectId;
    private int assignedId;
    private int createdId;
    private String code;
    private Timestamp created;
    private Timestamp updated;
    private Timestamp dueDate;
    private int estimation;
    private String description;
    private int statusId;
    private int priorityId;
    private Integer attachementId;
    private Project projectByProjectId;
    private Account accountByAssignedId;
    private Account accountByCreatedId;
    private Status statusByStatusId;
    private Priority priorityByPriorityId;
    private Attachments attachmentsByAttachementId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "project_id", nullable = false,insertable = false,updatable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "assigned_id", nullable = false,insertable = false,updatable = false)
    public int getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(int assignedId) {
        this.assignedId = assignedId;
    }

    @Basic
    @Column(name = "created_id", nullable = false,insertable = false,updatable = false)
    public int getCreatedId() {
        return createdId;
    }

    public void setCreatedId(int createdId) {
        this.createdId = createdId;
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
    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "updated", nullable = false)
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Basic
    @Column(name = "due_date", nullable = false)
    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "estimation", nullable = false)
    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "status_id", nullable = false,insertable = false,updatable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "priority_id", nullable = false,insertable = false,updatable = false)
    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    @Basic
    @Column(name = "attachement_id", nullable = true,insertable = false,updatable = false)
    public Integer getAttachementId() {
        return attachementId;
    }

    public void setAttachementId(Integer attachementId) {
        this.attachementId = attachementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                projectId == task.projectId &&
                assignedId == task.assignedId &&
                createdId == task.createdId &&
                estimation == task.estimation &&
                statusId == task.statusId &&
                priorityId == task.priorityId &&
                Objects.equals(code, task.code) &&
                Objects.equals(created, task.created) &&
                Objects.equals(updated, task.updated) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(description, task.description) &&
                Objects.equals(attachementId, task.attachementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, assignedId, createdId, code, created, updated, dueDate, estimation, description, statusId, priorityId, attachementId);
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "assigned_id", referencedColumnName = "id", nullable = false)
    public Account getAccountByAssignedId() {
        return accountByAssignedId;
    }

    public void setAccountByAssignedId(Account accountByAssignedId) {
        this.accountByAssignedId = accountByAssignedId;
    }

    @ManyToOne
    @JoinColumn(name = "created_id", referencedColumnName = "id", nullable = false)
    public Account getAccountByCreatedId() {
        return accountByCreatedId;
    }

    public void setAccountByCreatedId(Account accountByCreatedId) {
        this.accountByCreatedId = accountByCreatedId;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public Status getStatusByStatusId() {
        return statusByStatusId;
    }

    public void setStatusByStatusId(Status statusByStatusId) {
        this.statusByStatusId = statusByStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id", nullable = false)
    public Priority getPriorityByPriorityId() {
        return priorityByPriorityId;
    }

    public void setPriorityByPriorityId(Priority priorityByPriorityId) {
        this.priorityByPriorityId = priorityByPriorityId;
    }

    @ManyToOne
    @JoinColumn(name = "attachement_id", referencedColumnName = "id")
    public Attachments getAttachmentsByAttachementId() {
        return attachmentsByAttachementId;
    }

    public void setAttachmentsByAttachementId(Attachments attachmentsByAttachementId) {
        this.attachmentsByAttachementId = attachmentsByAttachementId;
    }
}
