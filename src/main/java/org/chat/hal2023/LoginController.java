package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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
     * The variable that allows this object to refer back to the mainController.
     */
    private MainSceneController mainSceneController = MainSceneController.getInstance();

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void logIn() {
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        if (userDAO.validateUser(username, password)) {
            User user = User.getInstance();
            user.setUsername(username);
            user.setPassword(password);

            mainSceneController.switchToScene(new ActionEvent(), "ChatBot.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte username en password in");
            alert.showAndWait();
        }
    }

    /**
     * Set the mainController of this LoginController.
     * @param mainSceneController scene controller of the application
     */
    public void setMainController(MainSceneController mainSceneController) {
        this.mainSceneController = mainSceneController;
    }

    @FXML
    private void switchToRegister() throws IOException {
        mainSceneController.switchToScene(new ActionEvent(), "Register.fxml");
    }


}
