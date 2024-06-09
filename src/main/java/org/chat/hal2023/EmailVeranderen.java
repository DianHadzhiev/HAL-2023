package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EmailVeranderen {

    User user = User.getInstance();

    private MainSceneController mainSceneController = MainSceneController.getInstance();

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
            user.setNewEmail(email.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email veranderd");
            alert.setHeaderText("Uw email is succesvol veranderd");
            alert.setContentText("Email veranderd");
            alert.showAndWait();
            mainSceneController.switchToScene(new ActionEvent(), "ChatBot.fxml");
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
