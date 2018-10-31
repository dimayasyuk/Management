package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Task;

public interface TaskService {
    Iterable<Task> getAllTasks();
    Task saveTask(Task task);
}
