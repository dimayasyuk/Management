package com.netcracker.edu.backend.fapi.service;

import com.netcracker.edu.backend.fapi.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User getUserByLogin(String login);
    User findUserByUsername(String username);
}
