package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Project;
import com.netcracker.edu.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService service;

    @Autowired
    public ProjectController(ProjectService service){
        this.service = service;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<Project> findAll() {
        return service.getAllProjects();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Project findProjectById(@PathVariable(name = "id") Long id) {
        return service.findProjectById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project saveProject(@RequestBody Project project){
        if(project != null){
            service.saveProject(project);
        }
        return null;
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProject(@PathVariable(name = "id") Long id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
