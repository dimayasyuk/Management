package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Account;
import com.netcracker.edu.backend.fapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService service;

    @RequestMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(service.getAllAccounts());
    }
}
