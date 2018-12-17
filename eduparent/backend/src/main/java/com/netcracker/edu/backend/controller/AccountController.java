package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.Account;
import com.netcracker.edu.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service){
        this.service = service;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<Account> findAll() {
        return service.getAllAccounts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account saveAccount(@RequestBody Account account){
        if(account != null){
            service.saveAccount(account);
        }
        return null;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Account findAccountByUser(@PathVariable(name = "id") String id) {
        return service.findAccountByUser(Long.valueOf(id));
    }

    @RequestMapping(value = "/email/{email}",method = RequestMethod.GET)
    public Account findAccountByEmail(@PathVariable(name = "email") String email) {
        return service.findAccountByEmail(email);
    }
}
