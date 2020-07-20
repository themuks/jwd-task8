package com.kuntsevich.task8.model.dao.impl;

import com.kuntsevich.task8.connect.ConnectorDB;
import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.model.dao.BookListDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlBookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "INSERT INTO `book_warehouse`.`book` (`title`, `genre`, `pagecount`, `authors`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getGenre());
                ps.setInt(3, book.getPageCount());
                ps.setString(4, String.join(" ", getAuthors(book)));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "DELETE FROM `book_warehouse`.`book` WHERE (`bookid` = ?)";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, book.getBookId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public Book findById(int id) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE bookid = ?";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    String title = rs.getString(2);
                    String genre = rs.getString(3);
                    int pageCount = rs.getInt(4);
                    List<String> authors = splitWords(rs.getString(5));
                    BookCreator bookCreator = new BookCreator();
                    try {
                        Book book = bookCreator.createBook(title, genre, pageCount, authors);
                        book.setBookId(id);
                        return book;
                    } catch (BookCreationException e) {
                        throw new DaoException("Can't create book");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE title = ?";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, title);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Book> result = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String genre = rs.getString(3);
                        int pageCount = rs.getInt(4);
                        List<String> authors = splitWords(rs.getString(5));
                        BookCreator bookCreator = new BookCreator();
                        try {
                            Book book = bookCreator.createBook(title, genre, pageCount, authors);
                            book.setBookId(id);
                            result.add(book);
                        } catch (BookCreationException e) {
                            throw new DaoException("Can't create book", e);
                        }
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public List<Book> findByGenre(String genre) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE genre = ?";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, genre);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Book> result = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String title = rs.getString(2);
                        int pageCount = rs.getInt(4);
                        List<String> authors = splitWords(rs.getString(5));
                        BookCreator bookCreator = new BookCreator();
                        try {
                            Book book = bookCreator.createBook(title, genre, pageCount, authors);
                            book.setBookId(id);
                            result.add(book);
                        } catch (BookCreationException e) {
                            throw new DaoException("Can't create book", e);
                        }
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public List<Book> findByPageCount(int pageCount) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE pagecount = ?";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, pageCount);
                try (ResultSet rs = ps.executeQuery()) {
                    List<Book> result = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String bookTitle = rs.getString(2);
                        String genre = rs.getString(3);
                        List<String> authors = splitWords(rs.getString(5));
                        BookCreator bookCreator = new BookCreator();
                        try {
                            Book book = bookCreator.createBook(bookTitle, genre, pageCount, authors);
                            book.setBookId(id);
                            result.add(book);
                        } catch (BookCreationException e) {
                            throw new DaoException("Can't create book", e);
                        }
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public List<Book> findByAuthors(List<String> authors) throws DaoException {
        try (Connection cn = ConnectorDB.getConnection()) {
            String sql = "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE authors = ?";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, String.join(" ", authors));
                try (ResultSet rs = ps.executeQuery()) {
                    List<Book> result = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String bookTitle = rs.getString(2);
                        String genre = rs.getString(3);
                        int pageCount = rs.getInt(4);
                        BookCreator bookCreator = new BookCreator();
                        try {
                            Book book = bookCreator.createBook(bookTitle, genre, pageCount, authors);
                            book.setBookId(id);
                            result.add(book);
                        } catch (BookCreationException e) {
                            throw new DaoException("Can't create book", e);
                        }
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("DB connection error", e);
        }
    }

    @Override
    public List<Book> sortById() {
        return null;
    }

    @Override
    public List<Book> sortByTitle() {
        return null;
    }

    @Override
    public List<Book> sortByGenre() {
        return null;
    }

    @Override
    public List<Book> sortByPageCount() {
        return null;
    }

    @Override
    public List<Book> sortByAuthors() {
        return null;
    }

    private List<String> getAuthors(Book book) {
        int count = book.getAuthorsCount();
        List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String author = book.getAuthor(i);
            result.add(author);
        }
        return result;
    }

    private List<String> splitWords(String text) {
        String[] words = text.split("//s+");
        return List.of(words);
    }
}
