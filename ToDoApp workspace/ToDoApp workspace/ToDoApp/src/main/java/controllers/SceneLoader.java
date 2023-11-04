package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
	Makes it easier to load scenes
*/

public class SceneLoader {
	public static int screenWidth = 800; // The ContentPositioner class uses it
	int screenHeight = 800;
	String appTitle = "ToDoApp";
	
	public void openScene(Node node, String sceneName) { // every time a new scene is loaded, this function is called
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("/resources/view/" + sceneName + ".fxml"));
	        Scene scene = new Scene(root, screenWidth, screenHeight);
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(scene);
	        stage.setTitle(appTitle);
	        scene.getStylesheets().add(getClass().getResource("/resources/css/application.css").toExternalForm());
	        Image icon = new Image("/images/icon.jpg");
			stage.getIcons().add(icon);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}