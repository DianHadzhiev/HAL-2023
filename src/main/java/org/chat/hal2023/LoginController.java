package org.chat.hal2023;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    // functie voor inloggen die erna chatScene toont op te mainStage

    private ChatBotApp app;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    private Stage primaryStage;

    private Scene chatScene;

    public void setMainApp(ChatBotApp app) {
        this.app = app;
    }

    @FXML
    private void logIn() {
        User user = new User();
        if (user.getUsername().equals(this.usernameInput.getText()) && user.getPassword().equals(this.passwordInput.getText())) {
            this.primaryStage.setScene(chatScene);
        }
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setChatScene(Scene scene) {
        this.chatScene = scene;
    }
}
