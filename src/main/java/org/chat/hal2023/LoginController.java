package org.chat.hal2023;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    /**
     * The variable that allows this object to see the user input in the
     * username text field in the application.
     */
    @FXML
    private TextField usernameInput;

    /**
     * The variable that allows this object to see the user input in the
     * password text field in the application.
     */
    @FXML
    private PasswordField passwordInput;

    /**
     * The variable that allows this object to refer back to the application.
     */
    private ChatBotApp app;

    @FXML
    private void logIn() {
        User user = User.getInstance();
        if (user.getUsername().equals(this.usernameInput.getText())
                && user.getPassword().equals(this.passwordInput.getText())) {
            this.app.switchToChatScene();
        }
    }

    /**
     * Set the app of this LoginController.
     * @param app the app to be added to this LoginController
     */
    public void setApp(ChatBotApp app) {
        this.app = app;
    }
}
