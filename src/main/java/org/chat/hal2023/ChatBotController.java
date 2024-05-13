package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatBotController {

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField inputField;

    private ChatBot chatBot = new ChatBot();

    @FXML
    private void sendMessage(ActionEvent event) {
        String message = inputField.getText();
        displayMessage("You: " + message);
        String response = chatBot.getResponse(message);
        displayMessage("Bot: " + response);
        inputField.clear();
    }

    private void displayMessage(String message) {
        chatListView.getItems().add(message);
    }


}