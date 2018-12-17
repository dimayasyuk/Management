package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.Account;

public interface AccountService {
    Iterable<Account> getAllAccounts();
    Account saveAccount(Account account);
    Account findAccountByUser(Long id);
    Account findAccountByEmail(String email);
}
