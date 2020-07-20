package com.kuntsevich.task8.controller.command.provider;

import com.kuntsevich.task8.controller.command.Command;
import com.kuntsevich.task8.controller.command.impl.EmptyCommand;
import com.kuntsevich.task8.controller.command.type.CommandType;

public class CommandProvider {

    public static Command defineCommand(String action) {
        if (action == null || action.isBlank()) {
            return new EmptyCommand();
        }
        CommandType currentType;
        try {
            currentType = CommandType.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new EmptyCommand();
        }
        Command current = currentType.getCommand();
        return current;
    }
}
