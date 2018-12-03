package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status,Long> {
    Status findStatusByName(String name);
}
