package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Priority;
import com.netcracker.edu.backend.model.Role;

public interface PriorityService {
    Iterable<Priority> getAllPriorities();
}
