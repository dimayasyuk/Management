package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.Account;
import com.netcracker.edu.backend.fapi.model.Project;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account saveAccount(Account account);
    Account getAccountByUserId(String id);
}
