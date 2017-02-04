package logIn;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import client.Client;
import client.ClientQuery;

public class CenterLogInPanel extends VBox {
	GridPane customerPanel = new GridPane();
	Text logoText, idNumberText, passwordText, wrongDataField;
	TextField passwordField, idNumberField;
	Button myLogInButton;
	public boolean isUserAvail, isUserLoggedIn;

	public CenterLogInPanel() {
		logoText = new Text("CarWash System");
		logoText.setId("carWashText");
		logoText.setTranslateX(-60);
		logoText.setTranslateY(10);
		customerPanel.add(logoText, 1, 0);

		idNumberText = new Text("ID Number");
		idNumberText.setId("dataText");
		idNumberText.setTranslateX(37);
		idNumberText.setTranslateY(72);
		customerPanel.add(idNumberText, 0, 0);

		passwordText = new Text("Password");
		passwordText.setId("dataText");
		passwordText.setTranslateX(37);
		passwordText.setTranslateY(88);
		customerPanel.add(passwordText, 0, 1);

		idNumberField = new TextField();
		idNumberField.setTranslateX(160);
		idNumberField.setTranslateY(13);
		customerPanel.add(idNumberField, 0, 2);

		passwordField = new TextField();
		passwordField.setTranslateX(160);
		passwordField.setTranslateY(38);
		customerPanel.add(passwordField, 0, 3);

		myLogInButton = new Button("Log in");
		myLogInButton.setId("myLogInButton");
		myLogInButton.setTranslateX(290);
		myLogInButton.setTranslateY(90);
		myLogInButton.setOnAction(e -> {
			isUserInSystemDatabase();
		});
		customerPanel.add(myLogInButton, 0, 4);

		wrongDataField = new Text("");
		wrongDataField.setTranslateX(-140);
		wrongDataField.setTranslateY(201);
		wrongDataField.setId("wrongDataText");
		customerPanel.add(wrongDataField, 1, 0);

		getChildren().add(customerPanel);
	}

	boolean isUserInSystemDatabase() {
		String idNumber = this.idNumberField.getText();
		String password = this.passwordField.getText();
		if (isLogInPanelProperlyFilled()) {
			sendQueryIsUserInSystemDatabase(idNumber, password);
		} else {
			setWrongDataField("Incorrect data");
		}
		return false;
	}

	private boolean isIDNumberFieldFilled() {
		return (idNumberField.getText().length() > 0 && idNumberField.getText()
				.length() < 10);
	}

	private boolean isPasswordFieldFilled() {
		return (passwordField.getText().length() > 0 && passwordField.getText()
				.length() < 31);
	}

	boolean isLogInPanelProperlyFilled() {
		return isIDNumberFieldFilled() && isPasswordFieldFilled();
	}

	public void setWrongDataField(String content) {
		wrongDataField.setText(content);
	}

	private void sendQueryIsUserInSystemDatabase(String idNumber,
			String password) {
		try {
			ClientQuery clientQuery = new ClientQuery("isUserInSystemDatabase");
			clientQuery.parameters[0] = idNumber;
			clientQuery.parameters[1] = password;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void clearFields() {
		idNumberField.clear();
		passwordField.clear();
	}

	public void clearTexts() {
		wrongDataField.setText("");
	}
}
