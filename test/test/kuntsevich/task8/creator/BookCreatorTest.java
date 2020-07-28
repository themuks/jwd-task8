package test.kuntsevich.task8.creator;

import com.kuntsevich.task8.creator.BookCreator;
import com.kuntsevich.task8.entity.Book;
import com.kuntsevich.task8.exception.BookCreationException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookCreatorTest {

    private BookCreator bookCreator = new BookCreator();

    @Test
    public void testCreateBookPositive() {
        try {
            Book actual = bookCreator.createBook(1, "Title", "genre", 100, List.of("Author"));
            Book expected = new Book(1, "Title", "genre", 100, List.of("Author"));
            assertEquals(actual, expected);
        } catch (BookCreationException e) {
            fail(e.toString());
        }
    }

    @Test(expectedExceptions = BookCreationException.class)
    public void testCreateBookException() throws BookCreationException {
        bookCreator.createBook("", "", -1, List.of(""));
    }
}