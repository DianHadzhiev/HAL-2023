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
    private Scene loginScene;

    /**
     * The scene holding the chat screen.
     */
    private Scene chatScene;

    /**
     * The primary stage for this application.
     */
    private Stage primaryStage;
    /**
     * Main controller for this application that is used to switch scenes
     * in the application.
     */
    private MainSceneController mainSceneController;

    UserDAO userDAO = new UserDAO();

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

        mainSceneController = MainSceneController.getInstance();
        mainSceneController.setStage(primaryStage);

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
        Parent loginRoot = loginLoader.load();

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("ChatBot.fxml"));
        Parent chatRoot = chatLoader.load();

        loginScene = new Scene(loginRoot, APP_WIDTH, APP_HEIGHT);
        chatScene = new Scene(chatRoot, APP_WIDTH, APP_HEIGHT);

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
