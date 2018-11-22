package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Task;
import com.netcracker.edu.backend.repository.TaskRepository;
import com.netcracker.edu.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        return repository.findTaskById(taskId);
    }

    @Override
    public Task saveTask(Task task) {
        return repository.save(task);
    }

    @Override
    public Iterable<Task> getAllTasksByProject(Long projectId) {
        return repository.findTasksByProjectId(projectId);
    }

    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
