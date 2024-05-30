package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChatBotController {

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField inputField;

    @FXML
    private TextField addTabNameField; // Zorg ervoor dat dit correct wordt geïnjecteerd

    private ChatBotResponse chatBot = new ChatBotResponse();

    @FXML
    private TabPane tabPane; // Zorg ervoor dat tabPane correct geïnjecteerd is

    @FXML
    private void sendMessage(ActionEvent event) {
        String message = inputField.getText();
        if (!message.trim().isEmpty()) {
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
    private void deleteTab(ActionEvent event) {
        // Get the currently selected tab
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        // Check if the selected tab is not null and not the "+" tab
        if (selectedTab != null && !"+".equals(selectedTab.getText())) {
            tabPane.getTabs().remove(selectedTab);
        }
    }

    @FXML
    private void addNewTab(ActionEvent event) {
        try {
            // Load the content for the new tab from the FXML template
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatTabContent.fxml"));
            AnchorPane newTabContent = loader.load();

            // Get the controller for the new tab content
            ChatTabController chatTabController = loader.getController();

            // Get the tab name from the input field
            String tabNameText = addTabNameField.getText().trim();
            if (!tabNameText.isEmpty()) {
                Tab newTab = new Tab(tabNameText);
                newTab.setContent(newTabContent);

                // Pass the TabPane and Tab to the controller
                chatTabController.setTabPane(tabPane);
                chatTabController.setTab(newTab);

                // Add the new tab to the TabPane before the "+" tab
                tabPane.getTabs().add(tabPane.getTabs().size() - 1, newTab);

                // Select the new tab
                tabPane.getSelectionModel().select(newTab);

                // Clear the text field after adding the new tab
                addTabNameField.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
