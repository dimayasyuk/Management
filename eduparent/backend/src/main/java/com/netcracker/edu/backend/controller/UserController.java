package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.model.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

}
