package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ChangePassword {

    User user = User.getInstance();

    private MainSceneController mainSceneController = MainSceneController.getInstance();

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    public void setPassword() {
        if (!user.checkNewPassword(password.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Eisen van het wachtwoord:" +
                    "\n - Minstens 8 tekens" +
                    "\n - Minstens 1 hoofdletter" +
                    "\n - Minstens 1 cijfer");
            alert.showAndWait();
        } else if (password.getText().isEmpty() || confirmPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
        } else if (password.getText().equals(confirmPassword.getText())) {
            user.setNewPassword(password.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password veranderd");
            alert.setHeaderText("Uw password is succesvol veranderd");
            alert.setContentText("Password veranderd");
            alert.showAndWait();
            mainSceneController.switchToScene(new ActionEvent(), "ChatBot.fxml");
        } else if (!password.getText().equals(confirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Passwords zijn niet gelijk!");
            alert.showAndWait();
        }
    }

}
