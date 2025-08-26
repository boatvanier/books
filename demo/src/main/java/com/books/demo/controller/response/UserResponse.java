package com.books.demo.controller.response;


import com.books.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    Long userId;
    String name;
    String email;
    String userName;

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(),
                String.format("%s %s", user.getFirstName(), user.getLastName()),
                user.getEmail(),
                user.getUserName());
    }
}
