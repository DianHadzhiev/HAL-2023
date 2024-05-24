package org.chat.hal2023;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    private ChatBotApp app;

    @FXML
    private void logIn() {
        User user = new User();
        if (user.getUsername().equals(this.usernameInput.getText()) && user.getPassword().equals(this.passwordInput.getText())) {
            this.app.switchToChatScene();
        }
    }

    public void setApp(ChatBotApp app) {
        this.app = app;
    }
}
