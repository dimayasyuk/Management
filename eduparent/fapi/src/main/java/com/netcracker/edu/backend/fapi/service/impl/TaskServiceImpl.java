package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.model.Task;
import com.netcracker.edu.backend.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Value("${backend.server.url}")
    private String backendUrl;

    @Override
    public List<Task> getAllTasks() {
        RestTemplate restTemplate = new RestTemplate();
        Task[] tasks = restTemplate.getForObject(backendUrl + "/api/tasks", Task[].class);
        return Arrays.asList(tasks);
    }

    @Override
    public Task getTaskById(Long taskId) {
        RestTemplate restTemplate = new RestTemplate();
        Task task = restTemplate.getForObject(backendUrl + "/api/tasks/id/" + taskId, Task.class);
        return task;
    }

    @Override
    public List<Task> getAllTasksByProject(Long projectId) {
        RestTemplate restTemplate = new RestTemplate();
        Task[] tasks = restTemplate.getForObject(backendUrl + "/api/tasks/" + projectId, Task[].class);
        return Arrays.asList(tasks);
    }

    @Override
    public Task saveTask(Task task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendUrl + "/api/tasks",task, Task.class).getBody();
    }

    @Override
    public void deleteTask(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendUrl + "/api/tasks/" + id);
    }
}
