package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project saveProject(Project project);
}
