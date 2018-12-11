package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Task;
import org.springframework.data.domain.Page;

public interface TaskService {
    Iterable<Task> getAllTasks();
    Task saveTask(Task task);
    Iterable<Task> getAllTasksByProject(Long projectId);
    Task getTaskById(Long taskId);
    Page<Task> getCurrentTasks(Long page,Long id);
    Page<Task> getSortingTasksByPriority(Long page,Long id,String direction);
    Page<Task> getSortingTasksByStatus(Long page,Long id,String direction);
    void deleteTask(Long id);
}
