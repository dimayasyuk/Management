package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Task;
import com.netcracker.edu.backend.repository.TaskRepository;
import com.netcracker.edu.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Task findTopByOrderByIdDesc() {
        return repository.findTopByOrderByIdDesc();
    }

    @Override
    public Iterable<Task> getAllTasksByProject(Long projectId) {
        return repository.findTasksByProjectId(projectId);
    }

    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Task> getCurrentTasks(Long page,Long id) {
        return repository.findTasksByProjectId(id,PageRequest.of(page.intValue(),3));
    }

    @Override
    public Page<Task> getSortingTasksByPriority(Long page, Long id,String direction) {
        return repository.findTasksByProjectId(id,PageRequest.of(page.intValue(), 3,Sort.Direction.valueOf(direction),"priority_id"));
    }

    @Override
    public Page<Task> getSortingTasksByStatus(Long page, Long id,String direction) {
        return repository.findTasksByProjectId(id,PageRequest.of(page.intValue(), 3,Sort.Direction.valueOf(direction),"status_id"));
    }

    @Override
    public Page<Task> getFilteringTasksByStatus(Long page, Long id, Long statusId) {
        return repository.findTasksByProjectIdAndStatusId(id,statusId,PageRequest.of(page.intValue(),3));
    }

    @Override
    public Page<Task> getFilteringTasksByPriority(Long page, Long id, Long priorityId) {
        return repository.findTasksByProjectIdAndPriorityId(id,priorityId,PageRequest.of(page.intValue(),3));
    }
}
