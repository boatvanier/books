package com.books.demo.service;

import com.books.demo.model.User;
import com.books.demo.model.UserAuth;
import com.books.demo.repository.UserJPARepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserJPARepository userJPARepository;

    public UserAuthService(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJPARepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("login failed");
        }

        return new UserAuth(user);
    }
}
