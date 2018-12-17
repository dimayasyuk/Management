package com.netcracker.edu.backend.fapi.controller;

import com.netcracker.edu.backend.fapi.model.User;
import com.netcracker.edu.backend.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @RequestMapping(value = "{login}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@PathVariable(name = "login") String login) {
        return ResponseEntity.ok(service.getUserByLogin(login));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> saveAccount(@RequestBody User user) {
        if (user != null) {
            User user1 = service.getUserByLogin(user.getLogin());
            if (user1 != null) {
                return null;
            }
            else {
                return ResponseEntity.ok(service.saveUser(user));
            }
        }
        return null;
    }

}
