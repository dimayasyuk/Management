package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Status;
import com.netcracker.edu.backend.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    private StatusService service;

    @Autowired
    public StatusController(StatusService service){
        this.service = service;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<Status> findAll() {
        return service.getAllStatusies();
    }
}
