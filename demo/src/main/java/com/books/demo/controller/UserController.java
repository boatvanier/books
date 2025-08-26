package com.books.demo.controller;

import java.util.List;

import com.books.demo.controller.request.LoginRequest;
import com.books.demo.controller.response.UserResponse;
import com.books.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "User management APIs")
@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

//    @GetMapping("/api/users")
//    public ResponseEntity<List<UserResponse>> getAllUsers(){
//        return ResponseEntity.ok(
//                service.findAllUsers()
//                        .stream()
//                        .map(UserResponse::toResponse)
//                        .toList()
//        );
//    }

    @GetMapping("/api/users")
    public ResponseEntity<Page<UserResponse>> getUsers(
            @RequestParam(required = false) String keywords,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort){

        Page<UserResponse> user =
                service.findUsers(keywords, page, size, sort)
                        .map(UserResponse::toResponse);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(
                service.findUserByUserId(userId)
                        .map(UserResponse::toResponse)
                        .orElse(null)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user) {
        return ResponseEntity.ok(service.login(user.getUsername(), user.getPassword()));
    }
}
