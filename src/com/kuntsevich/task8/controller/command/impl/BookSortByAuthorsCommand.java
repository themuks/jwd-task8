package com.kuntsevich.task8.controller.command.impl;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.entity.Response;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Map;

public class BookSortByAuthorsCommand implements Command {

    @Override
    public Response execute(Map<String, String> params) {
        Response response;
        List<Book> books = BookServiceImpl.getInstance().sortByAuthors();
        response = new Response(false, books);
        return response;
    }
}
