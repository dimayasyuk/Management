package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.Account;
import com.netcracker.edu.backend.fapi.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        Account[] accounts = restTemplate.getForObject(backendUrl + "/api/accounts/all",Account[].class);
        return Arrays.asList(accounts);
    }

    @Override
    public Account saveAccount(Account account) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendUrl + "/api/accounts",account,Account.class).getBody();
    }

    @Override
    public Account getAccountByUserId(String id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendUrl + "/api/accounts/" + id,Account.class);
    }

    @Override
    public Account getAccountByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendUrl + "/api/accounts/email/" + email,Account.class);
    }
}
