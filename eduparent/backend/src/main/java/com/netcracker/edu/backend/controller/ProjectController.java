package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Project;
import com.netcracker.edu.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/page/{page}",method = RequestMethod.GET)
    public ResponseEntity getCurrentProjects(@PathVariable(name = "page") String page){
        Page p = service.getCurrentProjects(Long.valueOf(page));
        List<Project> projects = p.getContent();
        return ResponseEntity.ok()
                .header("page", String.valueOf(p.getTotalElements()))
                .body(projects);
    }

    @RequestMapping(value = "/number",method = RequestMethod.GET)
    public Long getNumberOfProjects(){
        return service.getNumberOfProjects();
    }
}
