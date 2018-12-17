package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findProjectById(Long id);
    Project findProjectByCode(String code);
}
