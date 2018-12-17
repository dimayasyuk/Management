package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.Account;
import com.netcracker.edu.backend.repository.AccountRepository;
import com.netcracker.edu.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account findAccountByUser(Long id) {
        return repository.findAccountsByUserId(id);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return repository.findAccountByEmail(email);
    }
}
