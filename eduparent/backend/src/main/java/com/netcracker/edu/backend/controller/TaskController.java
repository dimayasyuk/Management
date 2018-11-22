package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Task;
import com.netcracker.edu.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

    @RequestMapping
    public Iterable<Task> findAll() {
        return service.getAllTasks();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Iterable<Task> getAllTasksByProject(@PathVariable(name = "id") Long projectId) {
        return service.getAllTasksByProject(projectId);
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public Task getTaskById(@PathVariable(name = "id") Long taskId) {
        return service.getTaskById(taskId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task saveTask(@RequestBody Task task){
        if(task != null){
            service.saveTask(task);
        }
        return null;
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTask(@PathVariable(name = "id") Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
