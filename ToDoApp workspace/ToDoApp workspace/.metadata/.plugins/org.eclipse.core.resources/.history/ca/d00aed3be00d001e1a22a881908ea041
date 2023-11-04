package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.User;
import user.UserService;

import java.util.Arrays;
import java.util.List;

/*
    Contains the methods that are called in the SignUpScreen.fxml
*/

public class SignUpScreenController {

	@FXML
	private TextField firstNameLabel;

	@FXML
	private TextField lastNameLabel;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField usernameRegistrationLabel;

	@FXML
	private Label loginErrorText;

	public void signUp(ActionEvent actionEvent) throws Exception { // It is called when the Register button is pressed in the SignUpScreen
		UserService userService = new UserService();

		List<TextField> textFields = Arrays.asList(firstNameLabel, lastNameLabel, passwordField, usernameRegistrationLabel);

		for (TextField textField : textFields) {
			if (textField.getText().isEmpty()) {
				new TextAnimationHandler().SetTextForAFewSeconds(loginErrorText, "All entries should be filled!", 5);
				return;
			}
		}

		User userToBeInserted = new User(firstNameLabel.getText(), lastNameLabel.getText(), passwordField.getText(), usernameRegistrationLabel.getText());
		userService.addUser(userToBeInserted);
		backFromSignUpScreen(actionEvent);
	}

	public void backFromSignUpScreen(ActionEvent actionEvent) { // It is called when the backButton is pressed in the SignUpScreen
		new SceneLoader().openScene((Node) actionEvent.getSource(), "LoginScreen");
	}
}
