package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.User;
import com.netcracker.edu.backend.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${backend.server.url}")
    private  String backendUrl;

    public List<User> getAllUsers(){
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject(backendUrl + "/api/all",User[].class);
        return Arrays.asList(users);
    }
}
