package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Register {

    @FXML
    public TextField username;

    @FXML
    public TextField email;

    @FXML
    public TextField password;

    @FXML
    public TextField confirmPassword;

    private final User user = User.getInstance();

    @FXML
    public boolean registreren() {

        if (!checkUsername()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte username in");
            alert.showAndWait();

        } else if (!checkEmail()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte email in");
            alert.showAndWait();
        } else if (!checkForMissingField()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
        } else if (validUser()) {
            user.setUsername(username.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            user.saveUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registratie succesvol");
            alert.setHeaderText("Registratie succesvol");
            alert.setContentText("Registratie succesvol");
            alert.showAndWait();
            switchToLogin();
            return true;
        }else if (!checkPassword()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte password in");
            alert.showAndWait();
        } else if (!checkConfirmPassword()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Passwords zijn niet gelijk!");
            alert.showAndWait();
        }
        return false;
    }

    /*
     * added these extra methods to make the code more readable and easier to test.
     *
     */

    /**
     * check if new user credentials are valid
     * @return boolean true if all fields are filled and in correct format else false
     */
    public boolean validUser() {
        return checkUsername() && checkEmail() && checkPassword() && checkConfirmPassword();
    }

    /**
     *
     * @return if username is in correct format
     */
    public boolean checkUsername() {
        return user.checkNewUsername(username.getText());
    }

    /**
     *
     * @return false if password is not in correct format
     */
    public boolean checkPassword() {
        return user.checkNewPassword(password.getText());
    }

    /**
     *
     * @return false if password is not in correct format
     */
    public boolean checkConfirmPassword() {
        return password.getText().equals(confirmPassword.getText());
    }

    /**
     *
     * @return false if email is not in correct format
     */
    public boolean checkEmail() {
        return user.checkNewEmail(email.getText());
    }

    /**
     * checks if all fields are filled
     * @return boolean true if some field is empty
     */
    public boolean checkForMissingField(){
        return username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty();
    }

    // Switch to Login screen
    public void switchToLogin() {
        MainSceneController mainSceneController = MainSceneController.getInstance();
        mainSceneController.switchToScene(new ActionEvent(), "LoginScherm.fxml");
    }

}
