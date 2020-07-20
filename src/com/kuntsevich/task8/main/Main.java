package com.kuntsevich.task8.main;

import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.model.dao.impl.SqlBookListDaoImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SqlBookListDaoImpl sqlBookListDao = new SqlBookListDaoImpl();
        BookCreator bookCreator = new BookCreator();
        try {
            sqlBookListDao.addBook(bookCreator.createBook("Test book title", "Test genre", 322, List.of("Test_Author")));
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (BookCreationException e) {
            e.printStackTrace();
        }
    }

}
