package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Status;

public interface StatusService {
    Iterable<Status> getAllStatusies();
    Status getStatusByName(String name);
}
