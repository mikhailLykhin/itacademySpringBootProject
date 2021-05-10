package com.itacademy.example.mappers;

import com.itacademy.example.dto.BookDto;
import com.itacademy.example.entity.Book;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BookMapper {

    public Book mapBook(BookDto source) {
        return Book.builder()
                .id(source.getId())
                .isbn(source.getIsbn())
                .name(source.getName())
                .author(source.getAuthor())
                .picture(source.getPicture())
                .description(source.getDescription())
                .build();

    }

    public BookDto mapBookDto(Book source) {
        return BookDto.builder()
                .id(source.getId())
                .isbn(source.getIsbn())
                .name(source.getName())
                .author(source.getAuthor())
                .picture(source.getPicture())
                .description(source.getDescription())
                .build();

    }

    public List<Book> mapAllBooks(List<BookDto> source) {
        return source.stream().map(BookMapper::mapBook).collect(Collectors.toList());
    }

    public List<BookDto> mapAllBooksDto(List<Book> source) {
        return source.stream().map(BookMapper::mapBookDto).collect(Collectors.toList());
    }
}
