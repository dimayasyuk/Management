package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Task;
import com.netcracker.edu.backend.model.User;
import com.netcracker.edu.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<Task> findAll() {
        return service.getAllTasks();
    }
}
