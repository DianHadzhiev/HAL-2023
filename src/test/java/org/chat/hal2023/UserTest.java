package org.chat.hal2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing the proper behaviour of the {@link User User} class.
 *
 * @author Anne
 */
public class UserTest {
    private User user;
    private UserDAO mockUserDAO;

    @BeforeEach
    public void setUp() {
        user = User.getInstance();
        mockUserDAO = Mockito.mock(UserDAO.class);
        user.userDAO = mockUserDAO;
    }

    @Test
    public void testCheckNewEmail_DecisionTable() {
        // Decision Table Testing
        user.setNewEmail("valid@example.com");
        assertTrue(user.checkNewEmail("valid@example.com"));
        assertFalse(user.checkNewEmail("invalid-email"));
        assertFalse(user.checkNewEmail(null));
        assertFalse(user.checkNewEmail("invalid@example"));
    }


    @Test
    public void testCheckNewUsernameDecisionTable() {
        // Rule 1: Valid username
        assertTrue(user.checkNewUsername("validUsername123"));

        // Rule 2: Invalid username (contains space)
        assertFalse(user.checkNewUsername("invalid username"));

        // Rule 3: Invalid username (null)
        assertFalse(user.checkNewUsername(null));

        // Rule 4: Invalid username (empty)
        assertFalse(user.checkNewUsername(""));
    }

    // Decision Table 2: Password Validation
    @Test
    public void testCheckNewPasswordDecisionTable() {
        // Rule 1: Valid password
        assertTrue(user.checkNewPassword("Password1"));

        // Rule 2: Invalid password (no uppercase)
        assertFalse(user.checkNewPassword("password1"));

        // Rule 3: Invalid password (no digits)
        assertFalse(user.checkNewPassword("Password"));

        // Rule 4: Invalid password (too short)
        assertFalse(user.checkNewPassword("Pass1"));

        // Rule 5: Invalid password (null)
        assertFalse(user.checkNewPassword(null));
    }

    // Equivalence Classes and Boundary Values for Email Validation
    @Test
    public void testCheckNewEmailEquivalenceAndBoundary() {
        // Equivalence Class: Valid email
        assertTrue(user.checkNewEmail("test@example.com"));

        // Equivalence Class: Invalid email (missing '@')
        assertFalse(user.checkNewEmail("test.example.com"));

        // Boundary Value: Invalid email (just '@')
        assertFalse(user.checkNewEmail("@"));

        // Boundary Value: Valid email (minimum valid length)
        assertTrue(user.checkNewEmail("a@b.co"));

        // Equivalence Class: Invalid email (null)
        assertFalse(user.checkNewEmail(null));
    }

    // Decision, Condition, and Multiple Condition Coverage for Setting Methods
    @Test
    public void testSetNewUsernameCoverage() {
        user.setNewUsername("validUsername123");
        assertEquals("validUsername123", user.getUsername());
        verify(mockUserDAO).updateUsername(user.getUsername(), "validUsername123");

        user.setNewUsername("invalid username");
        assertNotEquals("invalid username", user.getUsername());
        verify(mockUserDAO, times(0)).updateUsername(user.getUsername(), "invalid username");
    }

    @Test
    public void testSetNewPasswordCoverage() {
        user.setNewPassword("Password1");
        assertEquals("Password1", user.getPassword());
        verify(mockUserDAO).updatePassword(user.getPassword(), "Password1");

        user.setNewPassword("password");
        assertNotEquals("password", user.getPassword());
        verify(mockUserDAO, times(0)).updatePassword(user.getPassword(), "password");
    }

    @Test
    public void testSetNewEmailCoverage() {
        user.setNewEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
        verify(mockUserDAO).updateEmail(user.getEmail(), "test@example.com");

        user.setNewEmail("invalid-email");
        assertNotEquals("invalid-email", user.getEmail());
        verify(mockUserDAO, times(0)).updateEmail(user.getEmail(), "invalid-email");
    }
}
