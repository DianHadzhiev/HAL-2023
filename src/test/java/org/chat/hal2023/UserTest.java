package org.chat.hal2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the proper behaviour of the {@link User User} class.
 *
 * @author Anne
 */
public class UserTest {

    /**
     * When the email of the User is changed, the new email has to follow a
     * proper email format. This test ensures this actually happens.
     */
    @Test
    public void testEmailValidation() {
        User testUser = User.getInstance();
        assertEquals("email@domain.net", testUser.getEmail());
        assertTrue(testUser.checkNewEmail("newmail@newdomain.newnet"));
        assertEquals("newmail@newdomain.newnet", testUser.getEmail());
        assertFalse(testUser.checkNewEmail("bogusinput"));
        assertEquals("newmail@newdomain.newnet", testUser.getEmail());
    }
}
