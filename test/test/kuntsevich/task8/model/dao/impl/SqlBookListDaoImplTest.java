package test.kuntsevich.task8.model.dao.impl;

import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import com.kuntsevich.task8.exception.DaoException;
import com.kuntsevich.task8.model.dao.BookListDao;
import com.kuntsevich.task8.model.dao.impl.SqlBookListDaoImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SqlBookListDaoImplTest {

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
            int id = bookListDao.addBook(expected);
            expected.setBookId(id);
            Book actual = bookListDao.findById(id);
            assertEquals(actual, expected);
        } catch (BookCreationException | DaoException e) {
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
            bookListDao.removeBook(book);
            int actual = bookListDao.findAll().size() + 1;
            assertEquals(actual, expected);
        } catch (DaoException | BookCreationException e) {
            fail();
        }
    }

    @Test
    public void testFindById() {
        try {
            assertNotNull(bookListDao.findById(generatedId));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindAll() {
        try {
            assertNotNull(bookListDao.findAll());
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByTitle() {
        try {
            assertNotNull(bookListDao.findByTitle("Title"));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByGenre() {
        try {
            assertNotNull(bookListDao.findByGenre("genre"));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByPageCount() {
        try {
            assertNotNull(bookListDao.findByPageCount(100));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByAuthors() {
        try {
            assertNotNull(bookListDao.findByAuthors(List.of("author")));
        } catch (DaoException e) {
            fail();
        }
    }
}