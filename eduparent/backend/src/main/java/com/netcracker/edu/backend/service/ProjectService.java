package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Project;

public interface ProjectService {
    Iterable<Project> getAllProjects();
    Project saveProject(Project project);
}
