package com.kuntsevich.task8.creator;

import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.util.BookIdGenerator;
import com.kuntsevich.task8.validator.BookValidator;

import java.util.List;

public class BookCreator {

    public Book createBook(String title, String genre, int pageCount, List<String> authors) throws BookCreationException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isTitleValid(title)
                || !bookValidator.isGenreValid(genre)
                || !bookValidator.isPageCountValid(pageCount)
                || !bookValidator.isAuthorsValid(authors)) {
            throw new BookCreationException("Some of parameters are incorrect");
        }
        int id = BookIdGenerator.getInstance().generateId();
        return new Book(id, title, genre, pageCount, authors);
    }

    public Book createBook(int id, String title, String genre, int pageCount, List<String> authors) throws BookCreationException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdValid(id)
                || !bookValidator.isTitleValid(title)
                || !bookValidator.isGenreValid(genre)
                || !bookValidator.isPageCountValid(pageCount)
                || !bookValidator.isAuthorsValid(authors)) {
            throw new BookCreationException("Some of parameters are incorrect");
        }
        return new Book(id, title, genre, pageCount, authors);
    }
}
