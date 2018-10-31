package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.model.Status;
import com.netcracker.edu.backend.fapi.service.ProjectService;
import com.netcracker.edu.backend.fapi.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    private StatusService service;

    @RequestMapping
    public ResponseEntity<List<Status>> getAllStatuses(){
        return ResponseEntity.ok(service.getAllStatusies());
    }
}
