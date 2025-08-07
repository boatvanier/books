package com.books.demo.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    @Size(min=2, max=100, message = "title must be between 2 and 100 letters")
    private String title;

    @NotNull(groups = UpdateGroup.class)
    private String author;
    @NotNull(groups = UpdateGroup.class)
    private double price;
    @NotNull(groups = UpdateGroup.class)
    private int stock;

    private String image;
}