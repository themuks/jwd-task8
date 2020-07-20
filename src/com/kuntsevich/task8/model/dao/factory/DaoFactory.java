package com.kuntsevich.task8.model.dao.factory;

import com.kuntsevich.task8.model.dao.BookListDao;
import com.kuntsevich.task8.model.dao.impl.SqlBookListDaoImpl;

public class DaoFactory {

    private static volatile DaoFactory instance;

    private final BookListDao sqlBookListDao = new SqlBookListDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    instance = new DaoFactory();
                }
            }
        }
        return instance;
    }

    public BookListDao getBookListDao() {
        return sqlBookListDao;
    }
}
