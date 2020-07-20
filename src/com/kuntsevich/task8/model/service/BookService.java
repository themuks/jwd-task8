package com.kuntsevich.task8.model.service;

import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.ServiceException;

import java.util.List;

public interface BookService {

    void addBook(String title, String genre, String pageCount, String authors) throws ServiceException;

    void removeBook(String id, String title, String genre, String pageCount, String authors) throws ServiceException;

    Book findById(String id) throws ServiceException;

    List<Book> findByTitle(String title) throws ServiceException;

    List<Book> findByGenre(String genre) throws ServiceException;

    List<Book> findByPageCount(String pageCount) throws ServiceException;

    List<Book> findByAuthors(String authors) throws ServiceException;

    List<Book> sortById();

    List<Book> sortByTitle();

    List<Book> sortByGenre();

    List<Book> sortByPageCount();

    List<Book> sortByAuthors();
}
