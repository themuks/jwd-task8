package com.kuntsevich.task8.controller.entity;

import com.kuntsevich.task8.entity.Book;

import java.util.List;
import java.util.Objects;

public class Response {

    private boolean error;
    private List<Book> result;

    public Response(boolean error, List<Book> result) {
        this.error = error;
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Book getParam(int index) {
        return result.get(index);
    }

    public void setResultValue(int index, Book value) {
        result.set(index, value);
    }

    public int getResultCount() {
        return result.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Response response = (Response) o;
        return error == response.error &&
                result.equals(response.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Response{");
        sb.append("error=").append(error);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
