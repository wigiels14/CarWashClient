package register;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import client.Client;
import client.ClientQuery;

public class RegisterPanel extends GridPane {

	Button mySingUpButton, backButton;
	Text idNumberText, passwordText, wrongDataText, correctDataText, peselText;
	public TextField peselField, idNumberField;
	public PasswordField passwordField;

	private void createRegisterText() {
		Text registerText = new Text("Register");
		registerText.setId("carWashText");
		registerText.setTranslateX(165);
		registerText.setTranslateY(12);
		this.add(registerText, 0, 0);
	}

	private void createIDNumberText() {
		idNumberText = new Text("ID Number");
		idNumberText.setId("dataText");
		idNumberText.setTranslateX(52);
		idNumberText.setTranslateY(40);
		this.add(idNumberText, 0, 1);
	}

	private void createPasswordText() {
		passwordText = new Text("Password");
		passwordText.setTranslateX(52);
		passwordText.setTranslateY(69);
		passwordText.setId("dataText");
		this.add(passwordText, 0, 2);
	}

	private void createPasswordField() {
		passwordField = new PasswordField();
		passwordField.setTranslateX(7);
		passwordField.setId("Field");
		passwordField.setTranslateY(89);
		this.add(passwordField, 1, 1);
	}

	private void createIDNumberField() {
		idNumberField = new TextField();
		idNumberField.setTranslateX(7);
		idNumberField.setTranslateY(73);
		idNumberField.setId("Field");
		this.add(idNumberField, 1, 0);
	}

	private void createBackButton() {
		backButton = new Button("Back");
		backButton.setId("mySignUpButton");
		backButton.setTranslateX(37);
		backButton.setTranslateY(91);
		backButton.setOnAction(e-> {
			clearFields();
			clearLogInPanelFields();
			clearTexts();
			clearLogInPanelTexts();
			Client.setLogInSceneActive();
		});
		this.add(backButton, 0, 5);
	}

	private void createPeselField() {
		peselField = new TextField();
		peselField.setTranslateX(7);
		peselField.setTranslateY(117);
		peselField.setId("Field");
		this.add(peselField, 1, 2);
	}

	private void createPeselText() {
		peselText = new Text("Pesel");
		peselText.setTranslateX(52);
		peselText.setTranslateY(97);
		peselText.setId("dataText");
		this.add(peselText, 0, 3);
	}

	private void createSingUpButton() {
		mySingUpButton = new Button("Sing up");
		mySingUpButton.setId("mySignUpButton");
		mySingUpButton.setTranslateX(320);
		mySingUpButton.setTranslateY(120);

		mySingUpButton.setOnAction(e -> {
			String customerIDNumber = Client.registerPanel.idNumberField.getText();
			String customerPassword =  Client.registerPanel.passwordField.getText();
			String customerPesel = Client.registerPanel.peselField.getText();
			
			if(isRegisterPanelProperlyFilled()) {
			isCustomerAlreadyRegistered(customerIDNumber);
			}else {
				setIncorrectData();
			}
		});
		this.add(mySingUpButton, 0, 4);
	}

	private void createWrongDataText() {
		wrongDataText = new Text("");
		wrongDataText.setId("wrongDataText");
		wrongDataText.setTranslateX(170);
		wrongDataText.setTranslateY(91);
		this.add(wrongDataText, 0, 5);
	}

	private void createCorrectDataText() {
		correctDataText = new Text("");
		correctDataText.setId("wrongDataText");
		correctDataText.setTranslateX(170);
		correctDataText.setTranslateY(91);
		this.add(correctDataText, 0, 5);
	}

	public RegisterPanel() {
		createRegisterText();

		createIDNumberText();

		createPasswordText();

		createIDNumberField();

		createPasswordField();

		createPeselField();

		createBackButton();

		createPeselText();

		createSingUpButton();

		createWrongDataText();

		createCorrectDataText();

		this.setStyle("-fx-background-image: url('rounded_register.png');"
				+ "-fx-background-radius: 35px;");
	}

	private boolean isIDNumberFieldFilled() {
		return (idNumberField.getText().length() > 0 && idNumberField.getText()
				.length() < 10);
	}

	private boolean isPasswordFieldFilled() {
		return (passwordField.getText().length() > 0 && passwordField.getText()
				.length() < 31);
	}

	private boolean isPeselFieldFilled() {
		return (peselField.getText().length() > 0 && peselField.getText()
				.length() < 13);
	}

	private boolean isRegisterPanelProperlyFilled() {
		return isIDNumberFieldFilled() && isPasswordFieldFilled()
				&& isPeselFieldFilled();
	}

	public void setUserAlrExist() {
		correctDataText.setText("");
		wrongDataText.setText("User already exist");
	}

	public void setUserAdded() {
		correctDataText.setText("");
		wrongDataText.setText("User added");
	}

	private void setIncorrectData() {
		correctDataText.setText("");
		wrongDataText.setText("Incorrect data");
	}

	public void sendCreateCustomer() {
		try {
			String idNumber = Client.registerPanel.idNumberField.getText();
			String password = Client.registerPanel.passwordField.getText();
			String pesel = Client.registerPanel.peselField.getText();

			ClientQuery clientQuery = new ClientQuery("createCustomer");
			clientQuery.parameters[0] = idNumber;
			clientQuery.parameters[1] = password;
			clientQuery.parameters[2] = pesel;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void isCustomerAlreadyRegistered(String idNumber) {
		try {
			ClientQuery clientQuery = new ClientQuery(
					"isCustomerAlreadyRegistered");
			clientQuery.parameters[0] = idNumber;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void clearFields() {
		idNumberField.clear();
		peselField.clear();
		passwordField.clear();
	}

	private void clearTexts() {
		correctDataText.setText("");
		wrongDataText.setText("");
	}

	private void clearLogInPanelTexts() {
		Client.mainLogInPanel.myVBox.clearTexts();
	}

	private void clearLogInPanelFields() {
		Client.mainLogInPanel.myVBox.clearFields();
	}

}
