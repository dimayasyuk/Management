package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project saveProject(Project project);
    Project findProjectById(Long id);
    void deleteProject(Long id);
    Integer getNumberOfProjects();
    ResponseEntity getCurrentProjects(Long page);
}
