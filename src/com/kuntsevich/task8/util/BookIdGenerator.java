package com.kuntsevich.task8.util;

public class BookIdGenerator {

    private static volatile BookIdGenerator instance;

    private static int currentId = 1;

    private BookIdGenerator() {
    }

    public static BookIdGenerator getInstance() {
        if (instance == null) {
            synchronized (BookIdGenerator.class) {
                if (instance == null) {
                    instance = new BookIdGenerator();
                }
            }
        }
        return instance;
    }

    public int generateId() {
        return currentId++;
    }

    public static void setCurrentId(int currentId) {
        BookIdGenerator.currentId = currentId;
    }
}
