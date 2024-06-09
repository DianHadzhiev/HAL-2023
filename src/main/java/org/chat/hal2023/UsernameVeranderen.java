package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class UsernameVeranderen {

    User user = User.getInstance();

    private MainSceneController mainSceneController = MainSceneController.getInstance();



    @FXML
    private TextField username;

    @FXML
    private TextField confirmUsername;

    @FXML
    public void setUsername()  {

        if (!user.checkNewUsername(username.getText()))  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul een correcte username in");
            alert.showAndWait();
        } else if (username.getText().isEmpty() || confirmUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
        } else if (username.getText().equals(confirmUsername.getText())) {
            user.setNewUsername(username.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Username veranderd");
            alert.setHeaderText("Uw username is succesvol veranderd");
            alert.setContentText("Username veranderd");
            alert.showAndWait();
            mainSceneController.switchToScene(new ActionEvent(), "ChatBot.fxml");
        } else if (!username.getText().equals(confirmUsername.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Usernames zijn niet gelijk!");
            alert.showAndWait();
        }

    }

}
