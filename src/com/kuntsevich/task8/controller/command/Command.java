package com.kuntsevich.task8.controller.command;

import com.kuntsevich.task8.controller.entity.Response;

import java.util.Map;

public interface Command {

    Response execute(Map<String, String> params);
}
