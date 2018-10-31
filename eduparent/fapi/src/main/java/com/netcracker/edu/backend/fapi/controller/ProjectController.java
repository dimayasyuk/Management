package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Project> saveProject(@RequestBody Project project){
        if(project != null) {
            return ResponseEntity.ok(service.saveProject(project));
        }
        return null;
    }
}
