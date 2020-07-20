package com.kuntsevich.task8.controller.command.impl;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.entity.Response;

import java.util.ArrayList;
import java.util.Map;

public class EmptyCommand implements Command {

    @Override
    public Response execute(Map<String, String> params) {
        Response response = new Response(false, new ArrayList<>());
        return response;
    }
}
