package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    Project findProjectById(Long id);
}
