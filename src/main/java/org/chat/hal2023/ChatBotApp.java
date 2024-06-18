package org.chat.hal2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
     * The primary stage for this application.
     */
    private Stage primaryStage;
    /**
     * Main controller for this application that is used to switch scenes
     * in the application.
     */
    private MainSceneController mainSceneController;


    /**
     * Constructor that creates all the scenes need throughout the application.
     * @throws IOException the {@code load()} method from the {FXMLLoader}
     * class might throw an IOException.
     */
    public ChatBotApp() throws IOException {
        super();
    }

    /**
     * Required start method for a child of {@code Application}.
     * @param primaryStage the container which holds all elements in this
     *                     application.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("HAL-2023");
        Image icon = new Image ("https://img.freepik.com/free-vector/chatbot-chat-message-vectorart_78370-4104.jpg");
        primaryStage.getIcons().add(icon);

        Scene loginScene;

        mainSceneController = MainSceneController.getInstance();
        mainSceneController.setStage(primaryStage);

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
        Parent loginRoot = loginLoader.load();

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("ChatBot.fxml"));
        chatLoader.load();

        loginScene = new Scene(loginRoot, APP_WIDTH, APP_HEIGHT);

        Session chatController = chatLoader.getController();
        chatController.setStage(primaryStage);

        LoginController loginController = loginLoader.getController();
        loginController.setMainController(mainSceneController);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * Main method.
     * @param args args :)
     */
    public static void main(String[] args) {
        launch(args);
    }

}
