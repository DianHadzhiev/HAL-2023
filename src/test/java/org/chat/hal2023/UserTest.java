package org.chat.hal2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void testEmailValidation() {
        User testUser = new User();
        assertEquals("email@domain.net", testUser.getEmail());
        assertTrue(testUser.setEmail("newmail@newdomain.newnet"));
        assertEquals("newmail@newdomain.newnet", testUser.getEmail());
        assertFalse(testUser.setEmail("bogusinput"));
        assertEquals("newmail@newdomain.newnet", testUser.getEmail());
    }
}
