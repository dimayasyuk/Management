package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Project;
import com.netcracker.edu.backend.repository.ProjectRepository;
import com.netcracker.edu.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository){
        this.repository = repository;
    }
    @Override
    public Iterable<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return repository.save(project);
    }
}
