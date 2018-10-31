package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Account;
import com.netcracker.edu.backend.fapi.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Value("${backend.server.url}")
    private  String backendUrl;

    @Override
    public List<Account> getAllAccounts() {
        RestTemplate restTemplate = new RestTemplate();
        Account[] accounts = restTemplate.getForObject(backendUrl + "/api/all",Account[].class);
        return Arrays.asList(accounts);
    }
}
