package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Register {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    User user = User.getInstance();


    @FXML
    public void registreren() {
        MainSceneController mainSceneController = MainSceneController.getInstance();

        if (!user.checkNewUsername(username.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte username in");
            alert.showAndWait();
        } else if (!user.checkNewEmail(email.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte email in");
            alert.showAndWait();
        } else if (username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
        } else if (password.getText().equals(confirmPassword.getText())) {
            user.setUsername(username.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            user.saveUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registratie succesvol");
            alert.setHeaderText("Registratie succesvol");
            alert.setContentText("Registratie succesvol");
            alert.showAndWait();
            mainSceneController.switchToScene(new ActionEvent(), "LoginScherm.fxml");
        } else if (!password.getText().equals(confirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Passwords zijn niet gelijk!");
            alert.showAndWait();
        }

    }


}
