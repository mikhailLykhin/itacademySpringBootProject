package com.itacademy.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@Table(name = "books")
public class Book extends AEntity<Integer>{

    @Column(name = "isbn", nullable = true, length = 255)
    private String isbn;

    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @Column(name = "author", nullable = true, length = 255)
    private String author;

    @Column(name = "picture", nullable = true, length = 255)
    private String picture;

    @Column(name = "description", nullable = true, length = 255)
    private String description;
}
