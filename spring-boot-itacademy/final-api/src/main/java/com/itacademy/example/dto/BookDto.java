package com.itacademy.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookDto {

    private int id;
    private String isbn;
    private String name;
    private String author;
    private String picture;
    private String description;
}
