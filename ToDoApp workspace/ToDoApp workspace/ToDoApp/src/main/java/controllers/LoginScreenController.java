package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.User;
import user.UserService;

/*
	Contains the methods that are called in the LoginScreen.fxml
*/

public class LoginScreenController {

	@FXML
	private TextField usernameField;

	@FXML
	private Label loginErrorText;

	@FXML
	private PasswordField passwordField;

	public void login(ActionEvent actionEvent) { 
	    try {
	        User user = new UserService().getUserFromLogin(usernameField.getText(), passwordField.getText());
	        if (user != null) {
	            new TasksScreenController().generateTasksList(actionEvent);
	        } else {
	            throw new Exception("User does not exist!");
	        }
	    } catch (Exception e) {
	        new TextAnimationHandler().SetTextForAFewSeconds(loginErrorText, e.getMessage(), 5);
	    }
	}

	public void openRegisterMenu(ActionEvent actionEvent) {
		new SceneLoader().openScene((Node) actionEvent.getSource(), "SignUpScreen");

	}
}
