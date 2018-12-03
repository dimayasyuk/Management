package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Status;

import java.util.List;

public interface StatusService {
    List<Status> getAllStatusies();
    Status getStatusByName(String name);
}
