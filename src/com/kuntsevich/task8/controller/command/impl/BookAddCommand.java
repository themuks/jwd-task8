package com.kuntsevich.task8.controller.command.impl;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.entity.Response;
import com.kuntsevich.task8.exception.ServiceException;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Map;

public class BookAddCommand implements Command {

    private static final String PARAM_NAME_TITLE = "title";
    private static final String PARAM_NAME_GENRE = "genre";
    private static final String PARAM_NAME_PAGE_COUNT = "pageCount";
    private static final String PARAM_NAME_AUTHORS = "authors";

    @Override
    public Response execute(Map<String, String> params) {
        Response response;
        String title = params.get(PARAM_NAME_TITLE);
        String genre = params.get(PARAM_NAME_GENRE);
        String pageCount = params.get(PARAM_NAME_PAGE_COUNT);
        String authors = params.get(PARAM_NAME_AUTHORS);
        try {
            BookServiceImpl.getInstance().addBook(title, genre, pageCount, authors);
            response = new Response(false, new ArrayList<>());
        } catch (ServiceException e) {
            response = new Response(true, new ArrayList<>());
        }
        return response;
    }
}
