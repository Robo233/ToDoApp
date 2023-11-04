package controllers;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class TextAnimationHandler { 
	public void SetTextForAFewSeconds(Label label, String text, int duration) { // Called from the LoginScreenController and the SignUpScreenController, it changes the text of a TextField and after a few seconds it sets it back to be empty
		label.setText(text);
		PauseTransition pause = new PauseTransition(Duration.seconds(duration));
		pause.setOnFinished(event -> {
			label.setText("");
		});
		pause.play();
	}
}