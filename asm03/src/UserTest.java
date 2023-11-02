import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void validateCustomerId() {
        User user = new User("funix","001111111111");
        assertTrue(user.validateCustomerId(user.getCustomerId()));
        User user1 = new User("funix2","000111111111");
        assertFalse(user1.validateCustomerId(user1.getCustomerId()));
    }
}