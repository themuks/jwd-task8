package com.kuntsevich.task8.controller.command.type;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.command.impl.*;

public enum CommandType {

    ADD_BOOK_COMMAND(new BookAddCommand()),
    REMOVE_BOOK_COMMAND(new BookRemoveCommand()),
    FIND_BOOK_BY_ID_COMMAND(new BookFindByIdCommand()),
    FIND_BOOKS_BY_TITLE_COMMAND(new BookFindByTitleCommand()),
    FIND_BOOKS_BY_GENRE_COMMAND(new BookFindByGenreCommand()),
    FIND_BOOKS_BY_PAGE_COUNT_COMMAND(new BookFindByPageCountCommand()),
    FIND_BOOKS_BY_AUTHORS_COMMAND(new BookFindByAuthorsCommand()),
    SORT_BOOKS_BY_ID_COMMAND(new BookSortByIdCommand()),
    SORT_BOOKS_BY_TITLE_COMMAND(new BookSortByTitleCommand()),
    SORT_BOOKS_BY_GENRE_COMMAND(new BookSortByGenreCommand()),
    SORT_BOOKS_BY_PAGE_COUNT_COMMAND(new BookSortByPageCountCommand()),
    SORT_BOOKS_BY_AUTHORS_COMMAND(new BookSortByAuthorsCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
