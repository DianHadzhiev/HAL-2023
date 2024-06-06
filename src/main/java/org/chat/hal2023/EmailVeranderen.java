package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EmailVeranderen {

    User user = User.getInstance();

    private ChatBotApp app;

    @FXML
    private TextField email;

    @FXML
    private TextField confirmEmail;

    @FXML
    public void setEmail() {

        if(!user.checkNewEmail(email.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte email in");
            alert.showAndWait();
        }
        else if(email.getText().isEmpty() || confirmEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
        }
        else if(email.getText().equals(confirmEmail.getText())) {
            user.setEmail(email.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email veranderd");
            alert.setHeaderText("Uw email is succesvol veranderd");
            alert.setContentText("Email veranderd");
            alert.showAndWait();
            app.switchToChatScene();
        }
        else if(!email.getText().equals(confirmEmail.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Emails zijn niet gelijk!");
            alert.showAndWait();
        }

    }








}
