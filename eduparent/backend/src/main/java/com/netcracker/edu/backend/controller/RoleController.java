package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Role;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private RoleService service;

    @Autowired
    public RoleController(RoleService service){
        this.service = service;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<Role> findAll() {
        return service.getAllRoles();
    }
}
