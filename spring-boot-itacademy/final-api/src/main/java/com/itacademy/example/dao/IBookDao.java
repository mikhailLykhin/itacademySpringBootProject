package com.itacademy.example.dao;

import com.itacademy.example.entity.Book;

public interface IBookDao extends IAGenericDao<Book>{

    Book getByIsbn(String isbn);
}
