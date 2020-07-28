package test.kuntsevich.task8.validator;

import com.kuntsevich.task8.validator.NumberValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class NumberValidatorTest {

    private NumberValidator numberValidator = new NumberValidator();

    @Test
    public void testIsNumberStringValidPositive() {
        assertTrue(numberValidator.isNumberStringValid("-1923"));
    }

    @Test
    public void testIsNumberStringValidNegative() {
        assertFalse(numberValidator.isNumberStringValid("1.2"));
    }
}