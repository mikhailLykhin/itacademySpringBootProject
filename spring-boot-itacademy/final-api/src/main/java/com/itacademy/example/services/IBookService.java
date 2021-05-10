package com.itacademy.example.services;

import com.itacademy.example.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {

    BookDto findBook(int id);

    BookDto findByIsbn(String isbn);

    List<BookDto> getBooks();

    boolean addBook(BookDto bookDto);
}
