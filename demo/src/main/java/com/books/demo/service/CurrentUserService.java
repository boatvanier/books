package com.books.demo.service;

import com.books.demo.model.User;
import com.books.demo.model.UserAuth;
import com.books.demo.repository.UserJPARepository;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
public class CurrentUserService {

    private final UserJPARepository userJPARepository;

    public CurrentUserService(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserAuth user) {
            return userJPARepository.findByUserName(user.getUsername());
        }
        return null;
    }

    public Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserAuth user) {
            return user.getUserId();
        }
        return null;
    }
}
