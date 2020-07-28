package com.kuntsevich.task8.model.service.impl;

import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.exception.ServiceException;
import com.kuntsevich.task8.model.dao.BookListDao;
import com.kuntsevich.task8.model.dao.factory.DaoFactory;
import com.kuntsevich.task8.model.service.BookService;
import com.kuntsevich.task8.validator.NumberValidator;

import java.util.List;

public class BookServiceImpl implements BookService {

    private static volatile BookServiceImpl instance;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            synchronized (BookServiceImpl.class) {
                if (instance == null) {
                    instance = new BookServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int addBook(String title, String genre, String pageCount, String authors) throws ServiceException {
        if (title == null || genre == null || pageCount == null || authors == null) {
            throw new ServiceException("Some of parameters are null");
        }
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(pageCount)) {
            throw new ServiceException("Invalid page count string value");
        }
        int pageCountValue = Integer.parseInt(pageCount);
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookCreator bookCreator = new BookCreator();
        Book book;
        try {
            book = bookCreator.createBook(title, genre, pageCountValue, authorsList);
        } catch (BookCreationException e) {
            throw new ServiceException("Can't create book", e);
        }
        try {
            return bookListDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Can't add book", e);
        }
    }

    @Override
    public void removeBook(String id, String title, String genre, String pageCount, String authors) throws ServiceException {
        if (title == null || genre == null || pageCount == null || authors == null) {
            throw new ServiceException("Some of parameters are null");
        }
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(pageCount) || !numberValidator.isNumberStringValid(id)) {
            throw new ServiceException("Invalid param string value");
        }
        int pageCountValue = Integer.parseInt(pageCount);
        int idValue = Integer.parseInt(id);
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookCreator bookCreator = new BookCreator();
        Book book;
        try {
            book = bookCreator.createBook(title, genre, pageCountValue, authorsList);
        } catch (BookCreationException e) {
            throw new ServiceException("Can't create book", e);
        }
        book.setBookId(idValue);
        try {
            bookListDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException("Can't remove book", e);
        }
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Books not found", e);
        }
    }

    @Override
    public Book findById(String id) throws ServiceException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(id)) {
            throw new ServiceException("Invalid id value");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        int intId = Integer.parseInt(id);
        try {
            return bookListDao.findById(intId);
        } catch (DaoException e) {
            throw new ServiceException("Book not found", e);
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws ServiceException {
        if (title == null) {
            throw new ServiceException("Title is null");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException("Books not found", e);
        }
    }

    @Override
    public List<Book> findByGenre(String genre) throws ServiceException {
        if (genre == null) {
            throw new ServiceException("Title is null");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findByGenre(genre);
        } catch (DaoException e) {
            throw new ServiceException("Books not found", e);
        }
    }

    @Override
    public List<Book> findByPageCount(String pageCount) throws ServiceException {
        NumberValidator numberValidator = new NumberValidator();
        if (!numberValidator.isNumberStringValid(pageCount)) {
            throw new ServiceException("Invalid page count value");
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        int intPageCount = Integer.parseInt(pageCount);
        try {
            return bookListDao.findByPageCount(intPageCount);
        } catch (DaoException e) {
            throw new ServiceException("Books not found", e);
        }
    }

    @Override
    public List<Book> findByAuthors(String authors) throws ServiceException {
        if (authors == null) {
            throw new ServiceException("Authors is null");
        }
        List<String> authorsList = splitWords(authors);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        try {
            return bookListDao.findByAuthors(authorsList);
        } catch (DaoException e) {
            throw new ServiceException("Books not found", e);
        }
    }

    private List<String> splitWords(String text) {
        String[] words = text.split("//s+");
        return List.of(words);
    }
}
