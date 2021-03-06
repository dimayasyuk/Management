package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.Account;
import com.netcracker.edu.backend.fapi.model.Project;
import com.netcracker.edu.backend.fapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        if(account != null) {
            return ResponseEntity.ok(service.saveAccount(account));
        }
        return null;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Account> getAccountByUser(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(service.getAccountByUserId(id));
    }

    @RequestMapping(value = "/email/{email}",method = RequestMethod.GET)
    public ResponseEntity<Account> getAccountByEmail(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(service.getAccountByEmail(email));
    }
}
