package com.books.demo.service;

import java.util.List;
import java.util.Optional;

import com.books.demo.model.User;
import com.books.demo.repository.UserJPARepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserJPARepository repository;

    public UserService(UserJPARepository repository) {
        this.repository = repository;
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
}
