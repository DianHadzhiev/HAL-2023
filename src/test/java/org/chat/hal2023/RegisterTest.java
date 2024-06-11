package org.chat.hal2023;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
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

    }

    @BeforeAll
    public static void initToolkit() {
        // Initialize the JavaFX toolkit if it hasn't been initialized yet
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {});
        }
    }

    @Test
    public void testValidUser() {
        user.setNewEmail("testemail@example.com");
        user.setNewUsername("testusername");
        user.setNewPassword("Testpassword1");
        register.setConfirmPassword("Testpassword1");
        assertTrue(register.validUser(user));
    }

    @Test
    public void testCheckConfirmPassword() {
        register.password.setText("password");
        register.confirmPassword.setText("password");
        assertTrue(register.checkConfirmPassword());
    }

    @Test
    public void testCheckEmail() {
        when(user.checkNewEmail("")).thenReturn(false);
        assertFalse(register.checkEmail());
    }

    @Test
    public void testCheckIfAllFieldsAreFilled() {
        assertFalse(register.checkIfAllFieldsAreFilled());
    }

    @Test
    public void testRegistreren() {
        // Mock user methods
        when(user.checkNewUsername(anyString())).thenReturn(true);
        when(user.checkNewEmail(anyString())).thenReturn(true);
        when(user.checkNewPassword(anyString())).thenReturn(true);
        doNothing().when(user).saveUser(any());

        // Mock alert
        Alert alertMock = mock(Alert.class);
        doNothing().when(alertMock).showAndWait();

        // Set text fields
        register.username.setText("testUser");
        register.email.setText("test@example.com");
        register.password.setText("password");
        register.confirmPassword.setText("password");

        // Call the method
        register.registreren();

        // Verify that the success alert is shown
        verify(alertMock, times(1)).showAndWait();
    }

    @Test
    public void testRegistreren_InvalidUser() {
        // Mock invalid username
        when(user.checkNewUsername(anyString())).thenReturn(false);

        // Mock alert
        Alert alertMock = mock(Alert.class);
        doNothing().when(alertMock).showAndWait();

        // Call the method
        register.registreren();

        // Verify that the error alert is shown
        verify(alertMock, times(1)).showAndWait();
    }

    // Add more test methods as needed
}
