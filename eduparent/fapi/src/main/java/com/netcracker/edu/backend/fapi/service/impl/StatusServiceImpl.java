package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.model.Status;
import com.netcracker.edu.backend.fapi.service.StatusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Value("${backend.server.url}")
    private  String backendUrl;

    @Override
    public List<Status> getAllStatusies() {
        RestTemplate restTemplate = new RestTemplate();
        Status[] statuses = restTemplate.getForObject(backendUrl + "/api/status/all",Status[].class);
        return Arrays.asList(statuses);
    }
}
