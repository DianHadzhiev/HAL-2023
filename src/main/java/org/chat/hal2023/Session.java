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

public class Session {

    @FXML
    private ListView<String> chatListView;

    @FXML
    private TextField inputField;

    @FXML
    private TextField addTabNameField; // Zorg ervoor dat dit correct wordt geïnjecteerd

    @FXML
    private TabPane tabPane; // Zorg ervoor dat tabPane correct geïnjecteerd is

    @FXML
    private Tab tab;

    private final User user = User.getInstance();

    private LanguageManager languageManager = new LanguageManager();

    private ChatBotResponse chatBot = new ChatBotResponse(languageManager);


    /**
     * Setter for the {@link Session#tabPane tabPane} field.
     * @param tabPane TabPane object that needs to be set into the
     *                {@link Session#tabPane tabPane} field
     */
    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    /**
     * Setter for the {@link Session#tab tab} field.
     * @param tab Tab object that needs to be set into the
     *            {@link Session#tab tab} field
     */
    public void setTab(Tab tab) {
        this.tab = tab;
    }

    /**
     * Setter for the {@link Session#languageManager languageManager} field.
     * @param languageManager LanguageManager object that needs to be set into
     *                        the
     *                        {@link Session#languageManager languageManager}
     *                        field
     */
    public void setLanguageManager(LanguageManager languageManager) {
        this.languageManager = languageManager;
    }

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
            Session session = loader.getController();

            session.setLanguageManager(this.languageManager);

            // Get the tab name from the input field
            String tabNameText = addTabNameField.getText().trim();
            if (!tabNameText.isEmpty()) {
                Tab newTab = new Tab(tabNameText);
                newTab.setContent(newTabContent);

            // Pass the TabPane and Tab to the controller
            session.setTabPane(tabPane);
            session.setTab(newTab);

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

    public void changeUserName(ActionEvent event) {
        user.setUsername(inputField.getText());
    }

    public void changeUserEmail(ActionEvent event) {
        user.setEmail(inputField.getText());
    }

    public void changeUserPassword(ActionEvent event) {
        user.setPassword(inputField.getText());
    }

    @FXML
    public void setToEmail(ActionEvent event) {
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("EmailVeranderen.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void setToUsername(ActionEvent event) {
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("UsernameVeranderen.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void setToPassword(ActionEvent event) {
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("PasswordVeranderen.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
