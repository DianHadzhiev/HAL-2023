package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class ChatTabController {

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField inputField;

    private TabPane tabPane;

    private Tab tab;

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    @FXML
    private void sendMessage(ActionEvent event) {
        String message = inputField.getText();
        if (!message.trim().isEmpty()) {
            displayMessage("You: " + message);
            // Assuming there's a method to get a response from the ChatBot
            ChatBot chatBot = new ChatBot();
            String response = chatBot.getResponse(message);
            displayMessage("Bot: " + response);
            inputField.clear();
        }
    }

    private void displayMessage(String message) {
        chatListView.getItems().add(message);
    }

    @FXML
    private void deleteTab(ActionEvent event) {
        if (tabPane != null && tab != null && !"+" .equals(tab.getText())) {
            tabPane.getTabs().remove(tab);
        }
    }
}
