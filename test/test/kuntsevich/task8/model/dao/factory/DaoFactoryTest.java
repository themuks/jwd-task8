package test.kuntsevich.task8.model.dao.factory;

import com.kuntsevich.task8.model.dao.factory.DaoFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class DaoFactoryTest {

    @Test
    public void testGetBookListDao() {
        assertNotNull(DaoFactory.getInstance().getBookListDao());
    }
}