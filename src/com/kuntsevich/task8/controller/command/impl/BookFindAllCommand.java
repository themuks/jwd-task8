package com.kuntsevich.task8.controller.command.impl;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.entity.Response;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.ServiceException;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookFindAllCommand implements Command {

    @Override
    public Response execute(Map<String, String> params) {
        Response response;
        try {
            List<Book> books = BookServiceImpl.getInstance().findAll();
            response = new Response(false, books);
        } catch (ServiceException e) {
            response = new Response(true, new ArrayList<>());
        }
        return response;
    }
}
