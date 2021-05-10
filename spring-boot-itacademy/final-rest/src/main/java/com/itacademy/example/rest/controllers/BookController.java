package com.itacademy.example.rest.controllers;

import com.itacademy.example.dto.BookDto;
import com.itacademy.example.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class BookController {

    private IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/get")
    public String findBook(@RequestParam(value = "isbn") String isbn, Model model) {
        model.addAttribute("book", this.bookService.findByIsbn(isbn));
        return "bookPage";
    }

    @GetMapping("/book/new")
    public String newUser(@ModelAttribute("book") BookDto book) {
        return "newBook";
    }

    @PostMapping("/book/new")
    public String createUser(@ModelAttribute("book") BookDto book, Model model) {

        if (!bookService.addBook(book)){
            model.addAttribute("bookError", "Book already exists in data base");
            return "newBook";
        }

        return "redirect:/";
    }
}
