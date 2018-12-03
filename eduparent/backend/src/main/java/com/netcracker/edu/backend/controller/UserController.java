package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Iterable<User> findAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{login}",method = RequestMethod.GET)
    public User findUserByLogin(@PathVariable(name = "login") String login) {
        return userService.getUserByLogin(login);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user){
        if(user != null){
            userService.saveUser(user);
        }
        return null;
    }


    @RequestMapping(value = "/login/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUsername(@PathVariable(name = "username") String login) {
        User user = userService.getUserByLogin(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

}
