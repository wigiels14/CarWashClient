package Graphic.LogIn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class CenterLogInPanel extends VBox {
	GridPane usernamePanel = new GridPane();
	Text monopolyText, usernameText, logInText, wrongDataField;
	TextField passwordField, usernameField;
	Button myLogInButton;
	public boolean isUserAvail, isUserLoggedIn;

	public CenterLogInPanel() {
		monopolyText = new Text("CarWash System");
		monopolyText.setId("monopolyText");
		monopolyText.setTranslateX(-60);
		monopolyText.setTranslateY(10);
		usernamePanel.add(monopolyText, 1, 0);

		usernameText = new Text("Username");
		usernameText.setId("usernameText");
		usernameText.setTranslateX(37);
		usernameText.setTranslateY(72);
		usernamePanel.add(usernameText, 0, 0);

		logInText = new Text("Password");
		logInText.setId("usernameText");
		logInText.setTranslateX(37);
		logInText.setTranslateY(88);
		usernamePanel.add(logInText, 0, 1);

		usernameField = new TextField();
		usernameField.setTranslateX(160);
		usernameField.setTranslateY(15);
		usernamePanel.add(usernameField, 0, 2);

		passwordField = new TextField();
		passwordField.setTranslateX(160);
		passwordField.setTranslateY(45);
		usernamePanel.add(passwordField, 0, 3);

		myLogInButton = new Button("Log in");
		myLogInButton.setId("myLogInButton");
		myLogInButton.setTranslateX(290);
		myLogInButton.setTranslateY(90);
		usernamePanel.add(myLogInButton, 0, 4);

		wrongDataField = new Text("");
		wrongDataField.setTranslateX(-170);
		wrongDataField.setTranslateY(201);
		wrongDataField.setId("wrongDataText");
		usernamePanel.add(wrongDataField, 1, 0);

		getChildren().add(usernamePanel);
	}

}
