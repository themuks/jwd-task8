package com.kuntsevich.task8.model.dao;

import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.DaoException;

import java.util.List;

public interface BookListDao {

    void addBook(Book book) throws DaoException;

    void removeBook(Book book) throws DaoException;

    List<Book> findAll() throws DaoException;

    Book findById(int id) throws DaoException;

    List<Book> findByTitle(String title) throws DaoException;

    List<Book> findByGenre(String genre) throws DaoException;

    List<Book> findByPageCount(int pageCount) throws DaoException;

    List<Book> findByAuthors(List<String> authors) throws DaoException;
}
