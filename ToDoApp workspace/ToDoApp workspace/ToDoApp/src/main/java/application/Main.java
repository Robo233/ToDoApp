package application;

import controllers.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/*
	Starts the application
*/

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
        	Pane rootNode = new Pane();
            Scene initialScene = new Scene(rootNode);
            primaryStage.setScene(initialScene);
            primaryStage.show();
            primaryStage.setResizable(false);
            new SceneLoader().openScene(rootNode, "LoginScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}