package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.model.User;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserByLogin(String login) {
        return repository.findUserByLogin(login);
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
}
