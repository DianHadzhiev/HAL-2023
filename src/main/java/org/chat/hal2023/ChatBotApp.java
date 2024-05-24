package org.chat.hal2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatBotApp extends Application {

    private final Scene loginScene;

    private final Scene chatScene;

    private Stage primaryStage;

    public ChatBotApp() throws IOException {
        super();
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));

        Parent loginRoot = loginLoader.load();
        Parent chatRoot = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));

        this.loginScene = new Scene(loginRoot, 800, 600);
        this.chatScene = new Scene(chatRoot, 800, 600);

        LoginController loginController = loginLoader.getController();
        loginController.setApp(this);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("HAL-2023");
        this.primaryStage.setScene(loginScene);
        this.primaryStage.show();
    }

    public void switchToChatScene() {
        this.primaryStage.setScene(this.chatScene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
