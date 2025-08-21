package com.books.demo.service;

import java.util.List;
import java.util.Optional;


import com.books.demo.model.User;
import com.books.demo.repository.UserJPARepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserJPARepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserJPARepository repository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public List<User> findAllUsers(){
        return repository.findAll();
    }

    public Optional<User> findUserByUserId(Long userId) {
        return repository.findById(userId);
    }

    public void deleteUserByUserId(Long userId){
        repository.deleteById(userId);
    }

    public String login(String username, String password) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        }
        return "";
    }
}
