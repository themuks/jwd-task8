package com.kuntsevich.task8.controller.command.impl;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.entity.Response;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Map;

public class BookSortByIdCommand implements Command {

    @Override
    public Response execute(Map<String, String> params) {
        Response response;
        List<Book> books = BookServiceImpl.getInstance().sortById();
        response = new Response(false, books);
        return response;
    }
}
