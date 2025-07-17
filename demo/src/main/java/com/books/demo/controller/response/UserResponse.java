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
    String name;
    String email;

    public static UserResponse toResponse(User user){
        return new UserResponse(String.format("%s %s", user.getFirstName(), user.getLastName()),
        user.getEmail());
    }
}
