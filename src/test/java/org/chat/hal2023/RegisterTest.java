package org.chat.hal2023;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterTest {

    private Register register;
    private User user;

    @BeforeEach
    public void setUp() {
        register = new Register();
        register.username = new TextField();
        register.email = new TextField();
        register.password = new TextField();
        register.confirmPassword = new TextField();
        user = mock(User.class);
        //initToolkit();
    }

    @BeforeAll
    public static void initToolkit() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {});
        }
    }

    @Test
    public void testValidUser() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("Password123");
        assertTrue(register.validUser());
    }

    @Test
    public void testRegistreren_ValidUser() {
        Platform.runLater(() -> {
            register.username.setText("testUser");
            register.email.setText("test@example.com");
            register.password.setText("Password123");
            register.confirmPassword.setText("Password123");
            assertTrue(register.registreren());
        });
    }

    @Test
    public void testValidRandvoorwaarde() {
        register.username.setText("t");
        register.email.setText("t@e.c");
        register.password.setText("Password1");
        register.confirmPassword.setText("Password1");
        assertTrue(register.validUser());
    }

    @Test
    public void testValidRandvoorwaarde2() {
        register.username.setText("t");
        register.email.setText("t@e.");
        register.password.setText("Password1");
        register.confirmPassword.setText("Password1");
        assertFalse(register.validUser());
        //false because domain is missing
    }

    @Test
    public void testValidRandvoorwaarde3() {
        register.username.setText("t");
        register.email.setText("t@e.c");
        register.password.setText("Passworddddd");
        register.confirmPassword.setText("Passworddddd");
        assertFalse(register.validUser());
        //false because password must have 1 number
    }

    @Test
    public void testValidRandvoorwaarde4() {
        register.username.setText("t");
        register.email.setText("t@e.c");
        register.password.setText("Passwo1");
        register.confirmPassword.setText("Passwo1");
        assertFalse(register.validUser());
        //false because password must have 8 characters
    }

    @Test
    public void testRegistreren_InvalidUser() {
        register.username.setText("");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("Password123");
        doNothing().when(user).saveUser(any(User.class));
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testRegistreren_InvalidUser2() {
        register.username.setText("testUser");
        register.email.setText("invalid-email");
        register.password.setText("Password123");
        register.confirmPassword.setText("Password123");
        doNothing().when(user).saveUser(any(User.class));
        assertFalse(register.checkEmail());
    }

   @Test
    public void testRegistreren_InvalidUser3() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("");
        register.confirmPassword.setText("Password123");
        doNothing().when(user).saveUser(any(User.class));
        assertFalse(register.checkPassword());
    }

    @Test
    public void testRegistreren_InvalidUser4() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("");
        doNothing().when(user).saveUser(any(User.class));
        assertFalse(register.checkConfirmPassword());
    }

    @Test
    public void testRegistreren_InvalidUser5() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("Password123");
        doNothing().when(user).saveUser(any(User.class));
        assertTrue(register.checkPassword());
    }


    @Test
    public void testCheckIfAllFieldsAreFilled() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("Password123");
        assertFalse(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled() {
        register.username.setText("");
        register.email.setText("");
        register.password.setText("");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled2() {
        register.username.setText("");
        register.email.setText("");
        register.password.setText("");
        register.confirmPassword.setText("Password123");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled3() {
        register.username.setText("");
        register.email.setText("test@example.com");
        register.password.setText("");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled4() {
        register.username.setText("testUser");
        register.email.setText("");
        register.password.setText("");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled5() {
        register.username.setText("");
        register.email.setText("");
        register.password.setText("Password123");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled6() {
        register.username.setText("");
        register.email.setText("");
        register.password.setText("");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled7() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("Password123");
        assertFalse(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled8() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled9() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("");
        register.confirmPassword.setText("Password123");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled10() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled11() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("Password123");
        register.confirmPassword.setText("");
        assertTrue(register.checkForMissingField());
    }

    @Test
    public void testCheckIfAllFieldsAreNotFilled12() {
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("");
        register.confirmPassword.setText("Password123");
        assertTrue(register.checkForMissingField());
    }

}
