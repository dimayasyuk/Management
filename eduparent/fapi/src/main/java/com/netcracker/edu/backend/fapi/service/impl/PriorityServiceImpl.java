package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Priority;
import com.netcracker.edu.backend.fapi.service.PriorityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    @Value("${backend.server.url}")
    private  String backendUrl;

    @Override
    public List<Priority> getAllPriorities() {
        RestTemplate restTemplate = new RestTemplate();
        Priority[] priorities = restTemplate.getForObject(backendUrl + "/api/priority/all",Priority[].class);
        return Arrays.asList(priorities);
    }
}
