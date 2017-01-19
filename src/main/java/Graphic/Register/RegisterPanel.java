package Graphic.Register;

import java.io.IOException;
import java.sql.SQLException;

import Starting.JavaFX;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;

public class RegisterPanel extends GridPane {

	
	Button mySingUpButton, backButton;
	Text usernameText,  passwordText, wrongDataText, correctDataText, emailText;
	public TextField emailField, usernameField;
	public PasswordField passwordField;
	public boolean userNameAvaible;


	void createMonopolyText() {
		Text monopolyText = new Text("Register");
		monopolyText.setId("monopolyText");
		monopolyText.setTranslateX(165);
		monopolyText.setTranslateY(12);
		this.add(monopolyText, 0, 0);
	}

	/**
	 * Creating @see Text with text 'Username'.
	 */
	void createUserNameText() {
		usernameText = new Text("Username");
		usernameText.setId("usernameText");
		usernameText.setTranslateX(52);
		usernameText.setTranslateY(40);
		this.add(usernameText, 0, 1);
	}

	/**
	 * Creating @see Text with text 'Password'.
	 */
	void createPasswordText() {
		passwordText = new Text("Password");
		passwordText.setTranslateX(52);
		passwordText.setTranslateY(69);
		passwordText.setId("usernameText");
		this.add(passwordText, 0, 2);
	}

	/**
	 * Creating @see MyPasswordField.
	 */
	void createPasswordField() {
		passwordField = new PasswordField();
		passwordField.setTranslateX(7);
		passwordField.setTranslateY(89);
		this.add(passwordField, 1, 1);
	}

	/**
	 * Creating @see UsernameField.
	 */
	void createUserNameField() {
		usernameField = new TextField();
		usernameField.setTranslateX(7);
		usernameField.setTranslateY(73);
		this.add(usernameField, 1, 0);
	}

	/**
	 * Creating @see TextField for email.
	 */
	void createEmailField() {
		emailField = new TextField();
		emailField.setPromptText("example@gmail.com");
		emailField.setTranslateX(7);
		emailField.setTranslateY(117);
		emailField.setId("Field");
		this.add(emailField, 1, 2);
	}

	/**
	 * Creating @see Text with text 'E-mail'.
	 */
	void createEmailText() {
		emailText = new Text("E-mail");
		emailText.setTranslateX(52);
		emailText.setTranslateY(97);
		emailText.setId("usernameText");
		this.add(emailText, 0, 3);
	}

	/**
	 * Creating sing up button.
	 */
	void createSingUpButton() {
		mySingUpButton = new Button("Sing up");
		mySingUpButton.setId("mySignUpButton");
		mySingUpButton.setTranslateX(320);
		mySingUpButton.setTranslateY(120);
		this.add(mySingUpButton, 0, 4);
	}

	/**
	 * Creating button changing view to log in panel.
	 */
	void createBackButton() {
		backButton = new Button("Back");
		backButton.setId("mySignUpButton");
		backButton.setTranslateX(37);
		backButton.setTranslateY(91);
		backButton.setOnAction(e-> {
			JavaFX.primaryStage.setScene(JavaFX.logInPanelScene);
		});
		this.add(backButton, 0, 5);
	}

	/**
	 * Creating @see Text with incorrect data warning.
	 */
	void createWrongDataText() {
		wrongDataText = new Text("");
		wrongDataText.setId("wrongDataText");
		wrongDataText.setTranslateX(170);
		wrongDataText.setTranslateY(91);
		this.add(wrongDataText, 0, 5);
	}

	/**
	 * Creating @see text with correct data information.
	 */
	void createCorrectDataText() {
		correctDataText = new Text("");
		correctDataText.setId("wrongDataText");
		correctDataText.setTranslateX(170);
		correctDataText.setTranslateY(91);
		this.add(correctDataText, 0, 5);
	}

	/**
	 * Initializing pane - creating components, styling appearance.
	 */
	public  RegisterPanel() {
		createMonopolyText();

		createUserNameText();

		createPasswordText();

		createUserNameField();

		createPasswordField();

		createEmailField();

		createEmailText();

		createSingUpButton();

		createBackButton();

		createWrongDataText();

		createCorrectDataText();


		this.setStyle("-fx-background-image: url('rounded_register.png');" + "-fx-background-radius: 35px;");
	}


	/**
	 * Showing information about fact, that user with this data already exists.
	 */
	void setUserAlrExist() {
		correctDataText.setText("");
		wrongDataText.setText("User already exist");
	}

	/**
	 * Showing warning about inforrect data entered by user.
	 */
	void setIncorrectData() {
		correctDataText.setText("");
		wrongDataText.setText("Incorrect data");
	}


}
