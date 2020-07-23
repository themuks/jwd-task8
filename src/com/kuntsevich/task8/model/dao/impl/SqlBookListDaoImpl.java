package com.kuntsevich.task8.model.dao.impl;

import com.kuntsevich.task8.connect.ConnectorDb;
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

    private static final String INSERT_BOOK_QUERY =
            "INSERT INTO `book_warehouse`.`book` (`title`, `genre`, `pagecount`, `authors`) VALUES (?, ?, ?, ?)";
    private static final String DELETE_BOOK_QUERY =
            "DELETE FROM `book_warehouse`.`book` WHERE (`bookid` = ?)";
    private static final String FIND_ALL_BOOKS_QUERY =
            "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book";
    private static final String FIND_BOOK_BY_ID_QUERY =
            "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE bookid = ?";
    private static final String FIND_BOOK_BY_TITLE_QUERY =
            "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE title = ?";
    private static final String FIND_BOOK_BY_GENRE_QUERY =
            "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE genre = ?";
    private static final String FIND_BOOK_BY_PAGE_COUNT_QUERY =
            "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE pagecount = ?";
    private static final String FIND_BOOK_BY_AUTHORS_QUERY =
            "SELECT bookid, title, genre, pagecount, authors FROM book_warehouse.book WHERE authors = ?";
    private static final String DELIMITER = " ";

    @Override
    public void addBook(Book book) throws DaoException {
        String sql = INSERT_BOOK_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getGenre());
            preparedStatement.setInt(3, book.getPageCount());
            preparedStatement.setString(4, String.join(DELIMITER, getAuthors(book)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        String sql = DELETE_BOOK_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        String sql = FIND_ALL_BOOKS_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String genre = resultSet.getString(3);
                int pageCount = resultSet.getInt(4);
                List<String> authors = splitWords(resultSet.getString(5));
                BookCreator bookCreator = new BookCreator();
                try {
                    Book book = bookCreator.createBook(id, title, genre, pageCount, authors);
                    books.add(book);
                } catch (BookCreationException e) {
                    throw new DaoException("Can't create book", e);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
        return books;
    }

    @Override
    public Book findById(int id) throws DaoException {
        Book book;
        String sql = FIND_BOOK_BY_ID_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new DaoException("Book not found");
                }
                String title = resultSet.getString(2);
                String genre = resultSet.getString(3);
                int pageCount = resultSet.getInt(4);
                List<String> authors = splitWords(resultSet.getString(5));
                BookCreator bookCreator = new BookCreator();
                try {
                    book = bookCreator.createBook(id, title, genre, pageCount, authors);
                } catch (BookCreationException e) {
                    throw new DaoException("Can't create book", e);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
        return book;
    }

    @Override
    public List<Book> findByTitle(String title) throws DaoException {
        List<Book> books = new ArrayList<>();
        String sql = FIND_BOOK_BY_TITLE_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String genre = resultSet.getString(3);
                    int pageCount = resultSet.getInt(4);
                    List<String> authors = splitWords(resultSet.getString(5));
                    BookCreator bookCreator = new BookCreator();
                    try {
                        Book book = bookCreator.createBook(id, title, genre, pageCount, authors);
                        books.add(book);
                    } catch (BookCreationException e) {
                        throw new DaoException("Can't create book", e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
        return books;
    }

    @Override
    public List<Book> findByGenre(String genre) throws DaoException {
        List<Book> books = new ArrayList<>();
        String sql = FIND_BOOK_BY_GENRE_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, genre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    int pageCount = resultSet.getInt(4);
                    List<String> authors = splitWords(resultSet.getString(5));
                    BookCreator bookCreator = new BookCreator();
                    try {
                        Book book = bookCreator.createBook(id, title, genre, pageCount, authors);
                        books.add(book);
                    } catch (BookCreationException e) {
                        throw new DaoException("Can't create book", e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
        return books;
    }

    @Override
    public List<Book> findByPageCount(int pageCount) throws DaoException {
        List<Book> books = new ArrayList<>();
        String sql = FIND_BOOK_BY_PAGE_COUNT_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pageCount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String genre = resultSet.getString(3);
                    List<String> authors = splitWords(resultSet.getString(5));
                    BookCreator bookCreator = new BookCreator();
                    try {
                        Book book = bookCreator.createBook(id, title, genre, pageCount, authors);
                        books.add(book);
                    } catch (BookCreationException e) {
                        throw new DaoException("Can't create book", e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
        return books;
    }

    @Override
    public List<Book> findByAuthors(List<String> authors) throws DaoException {
        List<Book> books = new ArrayList<>();
        String sql = FIND_BOOK_BY_AUTHORS_QUERY;
        try (Connection connection = ConnectorDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, String.join(" ", authors));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String genre = resultSet.getString(3);
                    int pageCount = resultSet.getInt(4);
                    BookCreator bookCreator = new BookCreator();
                    try {
                        Book book = bookCreator.createBook(id, title, genre, pageCount, authors);
                        books.add(book);
                    } catch (BookCreationException e) {
                        throw new DaoException("Can't create book", e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Database error", e);
        }
        return books;
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
