package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Project;
import org.springframework.data.domain.Page;

public interface ProjectService {
    Iterable<Project> getAllProjects();
    Project saveProject(Project project);
    Project findProjectById(Long id);
    Project findProjectByCode(String code);
    void deleteProject(Long id);
    Page<Project> getCurrentProjects(Long page);
    Long getNumberOfProjects();
}
