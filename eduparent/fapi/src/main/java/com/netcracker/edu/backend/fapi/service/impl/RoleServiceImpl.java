package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Role;
import com.netcracker.edu.backend.fapi.service.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Value("${backend.server.url}")
    private  String backendUrl;

    @Override
    public List<Role> getAllRoles() {
        RestTemplate restTemplate = new RestTemplate();
        Role[] roles = restTemplate.getForObject(backendUrl + "/api/roles/all",Role[].class);
        return Arrays.asList(roles);
    }
}
