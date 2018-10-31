package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Project;
import com.netcracker.edu.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST)
    public Project saveProject(@RequestBody Project project){
        if(project != null){
            service.saveProject(project);
        }
        return null;
    }
}
