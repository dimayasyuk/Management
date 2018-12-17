package com.netcracker.edu.backend.fapi.service.impl;

import com.netcracker.edu.backend.fapi.model.User;
import com.netcracker.edu.backend.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Value("${backend.server.url}")
    private  String backendUrl;


    @Override
    public User findUserByUsername(String username) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendUrl + "/api/users/login/" + username, User.class);
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {

        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    public List<User> getAllUsers(){
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject(backendUrl + "/api/users/all",User[].class);
        return Arrays.asList(users);
    }

    @Override
    public User getUserByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(backendUrl + "/api/users/" + login,User.class);
        user.setPassword(null);
        return user;
    }

    @Override
    public User saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendUrl + "/api/users",user,User.class).getBody();
    }
}
