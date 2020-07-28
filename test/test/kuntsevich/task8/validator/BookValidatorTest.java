package test.kuntsevich.task8.validator;

import com.kuntsevich.task8.validator.BookValidator;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookValidatorTest {

    private BookValidator bookValidator = new BookValidator();

    @Test
    public void testValidateIdPositive() {
        assertTrue(bookValidator.isIdValid(10));
    }

    @Test
    public void testValidateTitlePositive() {
        assertTrue(bookValidator.isTitleValid("How to ...?"));
    }

    @Test
    public void testValidateGenresPositive() {
        assertTrue(bookValidator.isGenreValid("Horror"));
    }

    @Test
    public void testValidatePageCountPositive() {
        assertTrue(bookValidator.isPageCountValid(200));
    }

    @Test
    public void testValidateAuthorsPositive() {
        assertTrue(bookValidator.isAuthorsValid(List.of("Freid", "Pushkin")));
    }

    @Test
    public void testValidateIdNegativeLeftBound() {
        assertFalse(bookValidator.isIdValid(-1));
    }

    @Test
    public void testValidateTitleNegativeLeftBound() {
        assertFalse(bookValidator.isTitleValid("1"));
    }

    @Test
    public void testValidateGenresNegativeLeftBound() {
        assertFalse(bookValidator.isGenreValid("I"));
    }

    @Test
    public void testValidatePageCountNegativeLeftBound() {
        assertFalse(bookValidator.isPageCountValid(10));
    }

    @Test
    public void testValidateAuthorsNegativeLeftBound() {
        assertFalse(bookValidator.isAuthorsValid(List.of("A")));
    }

    @Test
    public void testValidateIdNegativeRightBound() {
        assertFalse(bookValidator.isIdValid(10001));
    }

    @Test
    public void testValidateTitleNegativeRightBound() {
        assertFalse(bookValidator.isTitleValid(" ".repeat(201)));
    }

    @Test
    public void testValidateGenresNegativeRightBound() {
        assertFalse(bookValidator.isGenreValid(" ".repeat(17)));
    }

    @Test
    public void testValidatePageCountNegativeRightBound() {
        assertFalse(bookValidator.isPageCountValid(10001));
    }

    @Test
    public void testValidateAuthorsNegativeRightBound() {
        assertFalse(bookValidator.isAuthorsValid(List.of(" ".repeat(17))));
    }
}