package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.model.User;
import com.netcracker.edu.backend.fapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @RequestMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok(service.getAllProjects());
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(service.findProjectById(Long.valueOf(id)));
    }

    @RequestMapping(value = "/page/{page}",method = RequestMethod.GET)
    public ResponseEntity getCurrentProjects(@PathVariable(name = "page") String page){
        return ResponseEntity.ok(service.getCurrentProjects(Long.valueOf(page)));
    }

    @RequestMapping(value = "/number",method = RequestMethod.GET)
    public ResponseEntity<Integer> getNumberOfProjects(){
        return ResponseEntity.ok(service.getNumberOfProjects());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Project> saveProject(@RequestBody Project project){
        if(project != null) {
            return ResponseEntity.ok(service.saveProject(project));
        }
        return null;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable String id) {
        service.deleteProject(Long.valueOf(id));
    }
}
