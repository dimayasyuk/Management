package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    List<Task> getAllTasksByProject(Long projectId);
    Task getTaskById(Long taskId);
    Task saveTask(Task task);
    void deleteTask(Long id);
}
