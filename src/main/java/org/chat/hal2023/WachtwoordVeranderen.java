package org.chat.hal2023;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class WachtwoordVeranderen {

    User user = User.getInstance();

    private ChatBotApp app;

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
            alert.setContentText("Vul een correcte password in");
            alert.showAndWait();
        } else if (password.getText().isEmpty() || confirmPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Vul alle velden in");
            alert.showAndWait();
        } else if (password.getText().equals(confirmPassword.getText())) {
            user.setPassword(password.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password veranderd");
            alert.setHeaderText("Uw password is succesvol veranderd");
            alert.setContentText("Password veranderd");
            alert.showAndWait();
            app.switchToChatScene();
        } else if (!password.getText().equals(confirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Probeer opnieuw");
            alert.setContentText("Passwords zijn niet gelijk!");
            alert.showAndWait();
        }
    }
}
