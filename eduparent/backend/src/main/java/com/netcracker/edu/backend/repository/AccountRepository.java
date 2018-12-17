package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Account;
import com.netcracker.edu.backend.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findAccountsByUserId(Long id);
    Account findAccountByEmail(String email);
}
