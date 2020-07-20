package com.kuntsevich.task8.entity;

import java.util.ArrayList;
import java.util.List;

public class BookWarehouse {

    private static volatile BookWarehouse instance;

    private List<Book> books = new ArrayList<>();

    private BookWarehouse() {
    }

    public static BookWarehouse getInstance() {
        if (instance == null) {
            synchronized (BookWarehouse.class) {
                if (instance == null) {
                    instance = new BookWarehouse();
                }
            }
        }
        return instance;
    }

    public boolean add(Book book) {
        if (book == null || books.contains(book)) {
            return false;
        }
        books.add(book);
        return true;
    }

    public boolean remove(Book book) {
        return books.remove(book);
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    public void setBook(int index, Book book) {
        books.set(index, book);
    }

    public int getBooksCount() {
        return books.size();
    }

    public void clear() {
        books.clear();
    }
}
