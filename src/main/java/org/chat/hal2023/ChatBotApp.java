package org.chat.hal2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatBotApp extends Application {
    //variables
    private Stage mainStage;

    @Override
    public void start(Stage chatStage) throws Exception {
        this.mainStage = chatStage;
        showChatScene();
    }


    //hieronder staat de scene van de chatsysteem
    public void showChatScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        mainStage.setTitle("HAL-2023");
        mainStage.setScene(new Scene(root, 800, 600));
        mainStage.setResizable(false);
        mainStage.show();
    }


    //hieronder staat de scene van de login functie
    private void showLoginScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        mainStage.setTitle("Login");
        mainStage.setScene(new Scene(root, 300, 200));
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}