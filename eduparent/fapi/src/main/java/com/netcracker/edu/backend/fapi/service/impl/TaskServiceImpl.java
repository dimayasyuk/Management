package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.model.Task;
import com.netcracker.edu.backend.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
        return restTemplate.postForEntity(backendUrl + "/api/tasks", task, Task.class).getBody();
    }

    @Override
    public void deleteTask(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendUrl + "/api/tasks/" + id);
    }

    @Override
    public ResponseEntity getCurrentTasks(Long page, Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendUrl + "/api/tasks/page/" + page + "/id/" + id, Task[].class);
    }

    @Override
    public ResponseEntity getSortingTasksByPriotity(Long page, Long id, String direction) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendUrl + "/api/tasks/page/" + page + "/priority" + "/id/" + id + "/direction/" + direction, Task[].class);
    }

    @Override
    public ResponseEntity getSortingTasksByStatus(Long page, Long id, String direction) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendUrl + "/api/tasks/page/" + page + "/status" + "/id/" + id + "/direction/" + direction, Task[].class);
    }

    @Override
    public ResponseEntity getFilteringTasksByStatus(String page, String id, String status) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendUrl + "/api/tasks/page/" + page + "/id/" + id + "/status/" + status, Task[].class);
    }

    @Override
    public ResponseEntity getFilteringTasksByPriority(String page, String id, String priority) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendUrl + "/api/tasks/page/" + page + "/id/" + id + "/priority/" + priority, Task[].class);
    }
}
