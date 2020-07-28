package test.kuntsevich.task8.controller;

import com.kuntsevich.task8.controller.Controller;
import com.kuntsevich.task8.controller.entity.Request;
import com.kuntsevich.task8.controller.entity.Response;
import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.entity.BookWarehouse;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.exception.ServiceException;
import com.kuntsevich.task8.model.dao.BookListDao;
import com.kuntsevich.task8.model.dao.impl.SqlBookListDaoImpl;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static org.testng.Assert.fail;

public class ControllerTest {

    private Controller controller = new Controller();
    private BookListDao bookListDao = new SqlBookListDaoImpl();
    private BookCreator bookCreator = new BookCreator();

    private int generatedId;

    @BeforeClass
    public void beforeClass() {
        try {
            Book book = bookCreator.createBook("Title", "genre", 100, List.of("author"));
            generatedId = bookListDao.addBook(book);
        } catch (BookCreationException | DaoException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        try {
            Book book = bookCreator.createBook(generatedId, "Title", "genre", 100, List.of("author"));
            bookListDao.removeBook(book);
        } catch (BookCreationException | DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecuteTaskEmptyCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("INVALID_COMMAND", params);
        Response actual = controller.executeTask(request);
        Response expected = new Response(false, new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskRemoveBookCommand() {
        int id = -1;
        try {
            Book book = bookCreator.createBook("Title", "genre", 100, List.of("author"));
            id = bookListDao.addBook(book);
            book.setBookId(id);
        } catch (DaoException | BookCreationException e) {
            fail();
        }
        Map<String, String> params = new HashMap<>();
        params.put("id", Integer.toString(id));
        params.put("title", "Title");
        params.put("genre", "genre");
        params.put("pageCount", "100");
        params.put("authors", "author");
        Request request = new Request("REMOVE_BOOK_COMMAND", params);
        Response actual = controller.executeTask(request);
        Response expected = new Response(false, new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindAllBooksCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("FIND_ALL_BOOKS", params);
        Response actual = controller.executeTask(request);
        assertNotNull(actual);
    }

    @Test
    public void testExecuteTaskFindBookByIdCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("id", Integer.toString(generatedId));
        Request request = new Request("FIND_BOOK_BY_ID_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(generatedId, "Title", "genre", 100, List.of("author")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByTitleCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("title", "Title");
        Request request = new Request("FIND_BOOKS_BY_TITLE_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(generatedId, "Title", "genre", 100, List.of("author")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByGenreCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("genre", "genre");
        Request request = new Request("FIND_BOOKS_BY_GENRE_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(generatedId, "Title", "genre", 100, List.of("author")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByPageCountCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("pageCount", "100");
        Request request = new Request("FIND_BOOKS_BY_PAGE_COUNT_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(generatedId, "Title", "genre", 100, List.of("author")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByAuthorsCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("authors", "author");
        Request request = new Request("FIND_BOOKS_BY_AUTHORS_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(generatedId, "Title", "genre", 100, List.of("author")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }
}