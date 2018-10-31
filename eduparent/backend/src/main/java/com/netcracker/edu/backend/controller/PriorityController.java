package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Priority;
import com.netcracker.edu.backend.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {
    private PriorityService service;

    @Autowired
    public PriorityController(PriorityService service){
        this.service = service;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<Priority> findAll() {
        return service.getAllPriorities();
    }
}
