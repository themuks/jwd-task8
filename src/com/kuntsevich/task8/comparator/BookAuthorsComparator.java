package com.kuntsevich.task8.comparator;

import com.kuntsevich.task8.entity.Book;

import java.util.Comparator;

public class BookAuthorsComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        int count1 = o1.getAuthorsCount();
        int count2 = o2.getAuthorsCount();
        return Integer.compare(count1, count2);
    }
}
