package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChatBotController {

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField inputField;

    private ChatBot chatBot = new ChatBot();

    @FXML
    private void sendMessage(ActionEvent event) {
        String message = inputField.getText();
        if (!message.trim().isEmpty()) {
            message = inputField.getText();
            displayMessage("You: " + message);
            String response = chatBot.getResponse(message);
            displayMessage("Bot: " + response);
            inputField.clear();
        }
    }

    private void displayMessage(String message) {
        chatListView.getItems().add(message);
    }

    @FXML
    private TabPane tabPane;

    @FXML
    private void addNewTab(ActionEvent event) {
        try {
            // Load the content for the new tab from the FXML template
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatTabContent.fxml"));
            AnchorPane newTabContent = loader.load();

            // Create a new tab with the loaded content
            Tab newTab = new Tab("Chat " + tabPane.getTabs().size());
            newTab.setContent(newTabContent);

            // Add the new tab to the TabPane before the "+" tab
            tabPane.getTabs().add(tabPane.getTabs().size() - 1, newTab);

            // Select the new tab
            tabPane.getSelectionModel().select(newTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}