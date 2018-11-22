package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.model.User;

public interface UserService {
    Iterable<User> getAllUsers();
    User saveUser(User user);
    User getUserByLogin(String login);
}
