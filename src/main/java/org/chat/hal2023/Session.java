package org.chat.hal2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    private LanguageManager languageManager;

    private ChatBotResponse chatBot;

    private Stage stage;

    private MainSceneController mainSceneController = MainSceneController.getInstance();

    @FXML
    private Button languageButton;

    @FXML
    private Text languageText;

    public Session() {
        languageManager = LanguageManager.getInstance(new LanguageContext());
        chatBot = ChatBotResponse.getInstance(languageManager.getLanguageContext().getStrategy());
        languageManager.subscribe(chatBot);
    }

    /**
     * Setter for the {@link Session#stage stage} field.
     * @param stage Stage object that needs to be set into the
     *              {@link Session#stage stage} field
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

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

    @FXML
    public void setToEmail(ActionEvent event) {
        mainSceneController.switchToScene(event, "ChangeEmail.fxml");
    }

    @FXML
    public void setToUsername(ActionEvent event) {
        mainSceneController.switchToScene(event, "ChangeUsername.fxml");
    }

    @FXML
    public void setToPassword(ActionEvent event) {
        mainSceneController.switchToScene(event, "ChangePassword.fxml");
    }

    @FXML
    public void setToLogout(ActionEvent event) {
        mainSceneController.switchToScene(event, "LoginScherm.fxml");
    }

    @FXML
    private void changeLanguage(ActionEvent event) {
        this.languageManager.changeLanguageStrategy();
        updateLanguageText(); // Roep deze methode aan na het veranderen van de taal
    }

    private void updateLanguageText() {
        if (languageManager.getLanguageContext().getStrategy() instanceof LanguageDutch) {
            languageText.setText("NL");
            languageButton.setText("Taal");
        } else if (languageManager.getLanguageContext().getStrategy() instanceof LanguageEnglish) {
            languageText.setText("EN");
            languageButton.setText("Language");
        } else {
            throw new RuntimeException("Onbekende taalstrategie gedetecteerd");
        }
    }
}
