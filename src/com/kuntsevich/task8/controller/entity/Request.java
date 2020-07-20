package com.kuntsevich.task8.controller.entity;

import java.util.Map;
import java.util.Objects;

public class Request {

    private String command;
    private Map<String, String> params;

    public Request(String command, Map<String, String> params) {
        this.command = command;
        this.params = params;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return command.equals(request.command) &&
                params.equals(request.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, params);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Request{");
        sb.append("command='").append(command).append('\'');
        sb.append(", params=").append(params);
        sb.append('}');
        return sb.toString();
    }
}
