package com.kuntsevich.task8.main;

import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.model.dao.impl.SqlBookListDaoImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SqlBookListDaoImpl sqlBookListDao = new SqlBookListDaoImpl();
        BookCreator bookCreator = new BookCreator();
        try {
            Book book = bookCreator.createBook("Test book title", "Test genre", 322, List.of("Test_Author"));
            book.setBookId(12);
            sqlBookListDao.addBook(book);
            sqlBookListDao.removeBook(book);
            System.out.println(sqlBookListDao.findById(5));
            System.out.println(sqlBookListDao.findByTitle("Преступление и наказание"));
            System.out.println(sqlBookListDao.findByGenre("Psychology"));
            System.out.println(sqlBookListDao.findByPageCount(1225));
            System.out.println(sqlBookListDao.findByAuthors(List.of("Igor_Blinov", "Valery_Romanchik")));
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (BookCreationException e) {
            e.printStackTrace();
        }
    }
}
