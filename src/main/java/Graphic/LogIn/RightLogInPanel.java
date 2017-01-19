package Graphic.LogIn;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import Starting.JavaFX;

public class RightLogInPanel extends GridPane {
	Button mySignUpButton;
	Text registerText2, registerText;

	public RightLogInPanel() {
		this.minWidth(200);
		registerText = new Text("If u don't have");
		registerText.setId("registerText");
		registerText.setTranslateX(-40);
		registerText.setTranslateY(130);
		this.add(registerText, 0, 0);

		registerText2 = new Text("an account");
		registerText2.setId("registerText");
		registerText2.setTranslateX(-28);
		registerText2.setTranslateY(137);
		this.add(registerText2, 0, 1);

		mySignUpButton = new Button("Sign up");
		mySignUpButton.setId("mySignUpButton");
		mySignUpButton.setTranslateX(-60);
		mySignUpButton.setTranslateY(150);
		this.add(mySignUpButton, 0, 2);
		
		mySignUpButton.setOnAction(e -> {
			JavaFX.primaryStage.setScene(JavaFX.registerPanelScene);
		});

	}

}
