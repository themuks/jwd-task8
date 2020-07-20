package com.kuntsevich.task8.model.dao.impl;

import com.kuntsevich.task8.connect.ConnectorDB;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.model.dao.BookListDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlBookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws DaoException {
        Connection cn = null;
        try {
            cn = ConnectorDB.getConnection();
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
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new DaoException("Connection close error", e);
                }
            }
        }
    }

    @Override
    public void removeBook(Book book) throws DaoException {

    }

    @Override
    public Book findById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findByGenre(String genre) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findByPageCount(int pageCount) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findByAuthors(List<String> authors) throws DaoException {
        return null;
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
}
