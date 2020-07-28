package test.kuntsevich.task8.model.service.impl;

import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.exception.ServiceException;
import com.kuntsevich.task8.model.dao.BookListDao;
import com.kuntsevich.task8.model.dao.impl.SqlBookListDaoImpl;
import com.kuntsevich.task8.model.service.impl.BookServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class BookServiceImplTest {

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
    public void testAddBook() {
        try {
            Book expected = bookCreator.createBook("TestBookTitle", "genre", 100, List.of("author"));
            int id = BookServiceImpl.getInstance().addBook("TestBookTitle", "genre", "100", "author");
            expected.setBookId(id);
            Book actual = bookListDao.findById(id);
            assertEquals(actual, expected);
        } catch (BookCreationException | ServiceException | DaoException e) {
            fail();
        }
    }

    @Test
    public void testRemoveBook() {
        try {
            Book book = bookCreator.createBook("TestBookTitle", "genre", 100, List.of("author"));
            int id = bookListDao.addBook(book);
            int expected = bookListDao.findAll().size();
            book.setBookId(id);
            BookServiceImpl.getInstance().removeBook(Integer.toString(id), "TestBookTitle", "genre", "100", "author");
            int actual = BookServiceImpl.getInstance().findAll().size() + 1;
            assertEquals(actual, expected);
        } catch (DaoException | BookCreationException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindById() {
        try {
            assertNotNull(BookServiceImpl.getInstance().findById(Integer.toString(generatedId)));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindAll() {
        try {
            assertNotNull(BookServiceImpl.getInstance().findAll());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByTitle() {
        try {
            assertNotNull(BookServiceImpl.getInstance().findByTitle("Title"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByGenre() {
        try {
            assertNotNull(BookServiceImpl.getInstance().findByGenre("genre"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByPageCount() {
        try {
            assertNotNull(BookServiceImpl.getInstance().findByPageCount("100"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByAuthors() {
        try {
            assertNotNull(BookServiceImpl.getInstance().findByAuthors("author"));
        } catch (ServiceException e) {
            fail();
        }
    }
}