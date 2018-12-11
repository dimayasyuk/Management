package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    List<Task> getAllTasksByProject(Long projectId);
    Task getTaskById(Long taskId);
    Task saveTask(Task task);
    void deleteTask(Long id);
    ResponseEntity getCurrentTasks(Long page,Long id);
    ResponseEntity getSortingTasksByPriotity(Long page,Long id,String direction);
    ResponseEntity getSortingTasksByStatus(Long page,Long id,String direction);
}
