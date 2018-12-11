package com.netcracker.edu.backend.fapi.controller;


import com.netcracker.edu.backend.fapi.model.Task;
import com.netcracker.edu.backend.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @RequestMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasksByProject(@PathVariable(name = "id") Long projectId) {
        return ResponseEntity.ok(service.getAllTasksByProject(projectId));
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long taskId) {
        return ResponseEntity.ok(service.getTaskById(taskId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> saveTask(@RequestBody Task task){
        if(task != null) {
            return ResponseEntity.ok(service.saveTask(task));
        }
        return null;
    }

    @PreAuthorize("hasAnyRole()")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable String id) {
        service.deleteTask(Long.valueOf(id));
    }

    @RequestMapping(value = "/page/{page}/id/{id}",method = RequestMethod.GET)
    public ResponseEntity getCurrentTasks(@PathVariable(name = "page") String page,@PathVariable(name = "id") String id){
        return ResponseEntity.ok(service.getCurrentTasks(Long.valueOf(page),Long.valueOf(id)));
    }

    @RequestMapping(value = "/page/{page}/priority/id/{id}/direction/{direction}",method = RequestMethod.GET)
    public ResponseEntity getSortingTasksByPriotity(@PathVariable(name = "page") String page,
                                                    @PathVariable(name = "id") String id,@PathVariable(name = "direction") String direction){
        return ResponseEntity.ok(service.getSortingTasksByPriotity(Long.valueOf(page),Long.valueOf(id), direction));
    }

    @RequestMapping(value = "/page/{page}/status/id/{id}/direction/{direction}",method = RequestMethod.GET)
    public ResponseEntity getSortingTasksByStatus(@PathVariable(name = "page") String page,@PathVariable(name = "id") String id,
                                                  @PathVariable(name = "direction") String direction){
        return ResponseEntity.ok(service.getSortingTasksByStatus(Long.valueOf(page),Long.valueOf(id),direction));
    }
}
