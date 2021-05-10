package com.itacademy.example.service.services;

import com.itacademy.example.WebScraper;
import com.itacademy.example.dao.IBookDao;
import com.itacademy.example.dto.BookDto;
import com.itacademy.example.entity.Book;
import com.itacademy.example.mappers.BookMapper;
import com.itacademy.example.services.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class BookService implements IBookService {

    private final IBookDao bookDao;
    private final WebScraper webScraper;


    @Autowired
    public BookService(IBookDao bookDao, WebScraper webScraper) {
        this.bookDao = bookDao;
        this.webScraper = webScraper;
    }

    @Override
    public BookDto findBook(int id) {
        Book book = this.bookDao.get(id);
        return BookMapper.mapBookDto(book);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        Book book = this.bookDao.getByIsbn(isbn);
        return BookMapper.mapBookDto(book);
    }

    @Override
    public List<BookDto> getBooks() {
        return null;
    }

    @Override
    @Transactional
    public boolean addBook(BookDto bookDto) {
        Book bookFromDB = this.bookDao.getByIsbn(bookDto.getIsbn());
        if (bookFromDB != null) {
            return false;
        }
        this.bookDao.create(webScraper.getBookDetailsFromWeb(bookDto.getIsbn()));
        return true;

    }
}
