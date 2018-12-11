package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Project;
import com.netcracker.edu.backend.repository.ProjectRepository;
import com.netcracker.edu.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public Project findProjectById(Long id) {
        return repository.findProjectById(id);
    }

    @Override
    public void deleteProject(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Project> getCurrentProjects(Long page) {
       return repository.findAll(PageRequest.of(page.intValue(), 1));
    }

    @Override
    public Long getNumberOfProjects() {
        return repository.count();
    }
}
