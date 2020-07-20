package com.kuntsevich.task8.controller.command.impl;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.entity.Response;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.ServiceException;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookFindByIdCommand implements Command {

    private static final String PARAM_NAME_ID = "id";

    @Override
    public Response execute(Map<String, String> params) {
        Response response;
        String id = params.get(PARAM_NAME_ID);
        try {
            List<Book> result = new ArrayList<>();
            Book book = BookServiceImpl.getInstance().findById(id);
            result.add(book);
            response = new Response(false, result);
        } catch (ServiceException e) {
            response = new Response(true, new ArrayList<>());
        }
        return response;
    }
}
