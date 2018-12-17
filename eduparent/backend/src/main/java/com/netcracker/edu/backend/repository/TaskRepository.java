package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Task;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Iterable<Task> findTasksByProjectId(Long projectId);
    Page findTasksByProjectId(Long projectId, Pageable pageable);
    Task findTaskById(Long taskId);
    Task findTopByOrderByIdDesc();
    Page findTasksByProjectIdAndStatusId(Long projectId,Long statusId,Pageable pageable);
    Page findTasksByProjectIdAndPriorityId(Long projectId,Long priorityId,Pageable pageable);
}
