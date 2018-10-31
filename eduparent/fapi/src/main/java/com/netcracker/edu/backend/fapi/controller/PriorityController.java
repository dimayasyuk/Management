package com.netcracker.edu.backend.fapi.controller;


import com.netcracker.edu.backend.fapi.model.Priority;
import com.netcracker.edu.backend.fapi.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {
    @Autowired
    private PriorityService service;

    @RequestMapping
    public ResponseEntity<List<Priority>> getAllPriority(){
        return ResponseEntity.ok(service.getAllPriorities());
    }
}
