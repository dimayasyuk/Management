package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Task;
import com.netcracker.edu.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @RequestMapping
    public Iterable<Task> findAll() {
        return service.getAllTasks();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Iterable<Task> getAllTasksByProject(@PathVariable(name = "id") Long projectId) {
        return service.getAllTasksByProject(projectId);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Task getTaskById(@PathVariable(name = "id") Long taskId) {
        return service.getTaskById(taskId);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public Task saveTask(@RequestBody Task task) {
        if (task != null) {
            task.setUpdated(new Date(new java.util.Date().getTime()));
            Task tsk = service.saveTask(task);
            if (task.getId() == 0) {
                Task ticket = service.findTopByOrderByIdDesc();
                ticket.setCode(ticket.getCode() + ticket.getId());
                return service.saveTask(ticket);
            } else {
                return tsk;
            }

        }
        return null;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTask(@PathVariable(name = "id") Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page/{page}/id/{id}", method = RequestMethod.GET)
    public ResponseEntity getCurrentTasks(@PathVariable(name = "page") String page, @PathVariable(name = "id") String id) {
        Page p = service.getCurrentTasks(Long.valueOf(page), Long.valueOf(id));
        List<Task> tasks = p.getContent();
        return ResponseEntity.ok()
                .header("page", String.valueOf(p.getTotalElements()))
                .body(tasks);
    }

    @RequestMapping(value = "/page/{page}/priority/id/{id}/direction/{direction}", method = RequestMethod.GET)
    public ResponseEntity getSortingTasksByPriority(@PathVariable(name = "page") String page, @PathVariable(name = "id") String id, @PathVariable(name = "direction") String direction) {
        Page p = service.getSortingTasksByPriority(Long.valueOf(page), Long.valueOf(id), direction);
        List<Task> tasks = p.getContent();
        return ResponseEntity.ok()
                .header("page", String.valueOf(p.getTotalElements()))
                .body(tasks);
    }

    @RequestMapping(value = "/page/{page}/status/id/{id}/direction/{direction}", method = RequestMethod.GET)
    public ResponseEntity getSortingTasksByStatus(@PathVariable(name = "page") String page, @PathVariable(name = "id") String id, @PathVariable(name = "direction") String direction) {
        Page p = service.getSortingTasksByStatus(Long.valueOf(page), Long.valueOf(id), direction);
        List<Task> tasks = p.getContent();
        return ResponseEntity.ok()
                .header("page", String.valueOf(p.getTotalElements()))
                .body(tasks);
    }

    @RequestMapping(value = "/page/{page}/id/{id}/status/{status}", method = RequestMethod.GET)
    public ResponseEntity getFilteringTasksByStatus(@PathVariable(name = "page") String page, @PathVariable(name = "id") String id, @PathVariable(name = "status") String status) {
        Page p = service.getFilteringTasksByStatus(Long.valueOf(page), Long.valueOf(id), Long.valueOf(status));
        List<Task> tasks = p.getContent();
        return ResponseEntity.ok()
                .header("page", String.valueOf(p.getTotalElements()))
                .body(tasks);
    }

    @RequestMapping(value = "/page/{page}/id/{id}/priority/{priority}", method = RequestMethod.GET)
    public ResponseEntity getFilteringTasksByPriority(@PathVariable(name = "page") String page, @PathVariable(name = "id") String id, @PathVariable(name = "priority") String priority) {
        Page p = service.getFilteringTasksByPriority(Long.valueOf(page), Long.valueOf(id), Long.valueOf(priority));
        List<Task> tasks = p.getContent();
        return ResponseEntity.ok()
                .header("page", String.valueOf(p.getTotalElements()))
                .body(tasks);
    }

}
