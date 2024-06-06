package org.chat.hal2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatBotApp extends Application {

    /**
     * Set height of the application window.
     */
    private static final int APP_HEIGHT = 600;

    /**
     * Set width of the application window.
     */
    private static final int APP_WIDTH = 800;

    /**
     * The scene holding the login screen.
     */
    private final Scene loginScene;

    /**
     * The scene holding the chat screen.
     */
    private final Scene chatScene;

    /**
     * The primary stage for this application.
     */
    private Stage primaryStage;

    /**
     * Constructor that creates all the scenes need throughout the application.
     * @throws IOException the {@code load()} method from the {FXMLLoader}
     * class might throw an IOException.
     */
    public ChatBotApp() throws IOException {
        super();
        FXMLLoader loginLoader = new FXMLLoader(
                getClass().getResource("LoginScherm.fxml"));

        Parent loginRoot = loginLoader.load();
        Parent chatRoot = FXMLLoader.load(
                getClass().getResource("ChatBot.fxml"));

        this.loginScene = new Scene(loginRoot, APP_WIDTH, APP_HEIGHT);
        this.chatScene = new Scene(chatRoot, APP_WIDTH, APP_HEIGHT);

        LoginController loginController = loginLoader.getController();
        loginController.setApp(this);
    }

    /**
     * Required start method for a child of {@code Application}.
     * @param primaryStage the container which holds all elements in this
     *                     application.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("HAL-2023");
        this.primaryStage.setScene(loginScene);
        this.primaryStage.show();
    }

    /**
     * Switch from the current scene to the chat scene.
     */
    public void switchToChatScene() {
        this.primaryStage.setScene(this.chatScene);
    }

    /**
     * Main method.
     * @param args args :)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
