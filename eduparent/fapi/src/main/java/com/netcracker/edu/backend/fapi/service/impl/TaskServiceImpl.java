package com.netcracker.edu.backend.fapi.service.impl;

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
    private  String backendUrl;

    @Override
    public List<Task> getAllTasks() {
        RestTemplate restTemplate = new RestTemplate();
        Task[] tasks = restTemplate.getForObject(backendUrl + "/api/all", Task[].class);
        return Arrays.asList(tasks);
    }

}
