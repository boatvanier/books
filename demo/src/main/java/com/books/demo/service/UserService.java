package com.books.demo.service;

import java.util.List;
import java.util.Optional;


import com.books.demo.model.User;
import com.books.demo.repository.UserJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<User> findUsers(String keywords, int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());

        if (keywords == null || keywords.isBlank() )
            return repository.findAll(pageable);

        return repository.findByKeywords(keywords, pageable);
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
