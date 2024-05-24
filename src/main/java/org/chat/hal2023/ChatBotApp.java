package org.chat.hal2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatBotApp extends Application {
    //variables
    //private Stage mainStage;

    /*@Override
    public void start(Stage chatStage) throws Exception {
        this.mainStage = chatStage;
        showLoginScene();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));

        Parent loginRoot = loginLoader.load();
        Parent chatRoot = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));

        Scene loginScene = new Scene(loginRoot, 800, 600);
        Scene chatScene = new Scene(chatRoot, 800, 600);

        LoginController loginController = loginLoader.getController();
        loginController.setMainApp(this);
        loginController.setChatScene(chatScene);
        loginController.setPrimaryStage(primaryStage);

        primaryStage.setTitle("HAL-2023");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    //hieronder staat de scene van de chatsysteem
/*    public void showChatScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        mainStage.setTitle("HAL-2023");
        mainStage.setScene(new Scene(root, 800, 600));
        mainStage.setResizable(false);
        mainStage.show();
    }*/


    //hieronder staat de scene van de login functie
/*    private void showLoginScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        mainStage.setTitle("Login");
        mainStage.setScene(new Scene(root, 600, 400));
        mainStage.show();
    }*/


    public static void main(String[] args) {
        launch(args);
    }

}
