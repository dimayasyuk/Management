package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Status;
import com.netcracker.edu.backend.repository.StatusRepository;
import com.netcracker.edu.backend.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository repository;

    @Autowired
    public StatusServiceImpl(StatusRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Status> getAllStatusies() {
        return repository.findAll();
    }
}
