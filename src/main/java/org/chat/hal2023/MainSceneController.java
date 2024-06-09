package org.chat.hal2023;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 *  This class is used to switch between scenes in the application.
 *
 */

public class MainSceneController {

    /**
     * The stage of the application.
     */
    private Stage primaryStage;

    /**
     *  The instance of this class.
     */
    private static MainSceneController instance = null;

    /**
     * Constructor for the MainSceneController. which is private to prevent
     *  other classes from instantiating it. this is a singleton class.
     */
    private MainSceneController() {
    }

    /**
     *  Get the instance of this class.
     * @return the instance of this class
     */
    public static MainSceneController getInstance() {
        if (instance == null) {
            instance = new MainSceneController();
        }
        return instance;
    }

    /**
     * Set the stage of the application.
     * @param stage the stage of the application
     */
    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Switch to a new scene in the application.
     * @param event the event that triggers the switch
     * @param fxmlFile the name of the file of the new scene
     */
    public void switchToScene(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
