package Graphic.CustmerInterface.Optional;

import java.io.IOException;

import Server.ClientQuery;
import Starting.Client;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Business.Person.Customer;

public class ChangePersonalDataPanel extends GridPane {
	private Customer customer;
	private Button changeFirstNameButton, changePassword, changeLastNameButton;
	private Text customerPasswordText, firstNameText, lastNameText, peselText,
			idNumberText, messageText;
	public TextField changeFirstNameTextField, changePasswordTextField,
			changeLastNameTextField;

	public ChangePersonalDataPanel() {
	}

	public void initAll() {
		setCustomerFirstNameText();
		createChangeFirstNameField();
		setChangeFirstNameButton();
		setCustomerLastNameText();
		createChangeLastNameField();
		setChangeLastNameButton();
		setCustomerIdNumberText();
		setCustomerPeselText();
		setCustomerPasswordText();
		createChangePasswordField();
		setChangePasswordButton();
	}

	private boolean isFieldProperlyFilled(TextField field) {
		if (field.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private void setCustomerFirstNameText() {
		if (firstNameText == null) {
			if (customer.getFirstName() == null) {
				firstNameText = new Text("First Name: <empty>");
			} else {
				firstNameText = new Text("First name: "
						+ customer.getFirstName());
			}
			firstNameText.setId("dataText");
			firstNameText.setTranslateX(20);
			firstNameText.setTranslateY(40);
			this.add(firstNameText, 0, 0);
		} else {
			if (customer.getFirstName() == null) {
				firstNameText.setText("First Name: <empty>");
			} else {
				firstNameText.setText("First name: " + customer.getFirstName());
			}
		}
	}

	private void refreshTopCustomerInterfaceData() {
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setFirstNameText();
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setLastNameText();
	}

	private void setCustomerLastNameText() {
		if (lastNameText == null) {
			if (customer.getLastName() == null) {
				lastNameText = new Text("Last name: <empty>");
			} else {
				lastNameText = new Text("Last name: " + customer.getLastName());
			}
			lastNameText.setId("dataText");
			lastNameText.setTranslateX(20);
			lastNameText.setTranslateY(40);
			this.add(lastNameText, 0, 1);
		} else {
			if (customer.getLastName() == null) {
				lastNameText.setText("Last Name: <empty>");
			} else {
				lastNameText.setText("Last name: " + customer.getLastName());
			}
		}
	}

	private void setCustomerIdNumberText() {
		idNumberText = new Text("ID number: " + customer.getIdNumber());
		idNumberText.setId("dataText");
		idNumberText.setTranslateX(20);
		idNumberText.setTranslateY(40);
		this.add(idNumberText, 0, 2);
	}

	private void setCustomerPeselText() {
		peselText = new Text("PESEL : " + customer.getPesel());
		peselText.setId("dataText");
		peselText.setTranslateX(20);
		peselText.setTranslateY(40);
		this.add(peselText, 0, 3);
	}

	private void setCustomerPasswordText() {
		if (customerPasswordText == null) {
			customerPasswordText = new Text("Customer Password: "
					+ customer.getPassword());
			customerPasswordText.setId("dataText");
			customerPasswordText.setTranslateX(20);
			customerPasswordText.setTranslateY(40);
			this.add(customerPasswordText, 0, 4);
		} else {
			customerPasswordText.setText("Customer Password: "
					+ customer.getPassword());
		}
	}

	private void setMessageText(String message) {
		if (messageText == null) {
			messageText = new Text(message);
			messageText.setId("dataText");
			messageText.setTranslateX(20);
			messageText.setTranslateY(120);
			this.add(messageText, 0, 6);
		} else {
			messageText.setText(message);
		}
	}

	private void setChangeFirstNameButton() {
		changeFirstNameButton = new Button("Change first name");
		changeFirstNameButton.setId("leftPanelButton");
		changeFirstNameButton.setTranslateX(40);
		changeFirstNameButton.setTranslateY(40);
		changeFirstNameButton.setOnAction(e-> {
			if(isFieldProperlyFilled(this.changeFirstNameTextField)) {
				Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().setFirstName(changeFirstNameTextField.getText());
				setCustomerFirstNameText();
				String idNumber = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getIdNumber();
				String firstName = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getFirstName();
				sendChangeCustomerFirstName(idNumber, firstName);
				refreshTopCustomerInterfaceData();
				setMessageText("Data changed");
			}else {
				setMessageText("Incorrect data");
			}
		});
		this.add(changeFirstNameButton, 2, 0);
	}

	private void createChangeFirstNameField() {
		changeFirstNameTextField = new TextField();
		changeFirstNameTextField.setTranslateX(30);
		changeFirstNameTextField.setTranslateY(40);
		this.add(changeFirstNameTextField, 1, 0);
	}

	private void createChangeLastNameField() {
		changeLastNameTextField = new TextField();
		changeLastNameTextField.setTranslateX(30);
		changeLastNameTextField.setTranslateY(40);
		this.add(changeLastNameTextField, 1, 1);
	}

	private void createChangePasswordField() {
		changePasswordTextField = new TextField();
		changePasswordTextField.setTranslateX(30);
		changePasswordTextField.setTranslateY(40);
		this.add(changePasswordTextField, 1, 4);
	}

	private void setChangeLastNameButton() {
		changeLastNameButton = new Button("Change last name");
		changeLastNameButton.setId("leftPanelButton");
		changeLastNameButton.setTranslateX(40);
		changeLastNameButton.setTranslateY(40);
		changeLastNameButton.setOnAction(e-> {
			if(isFieldProperlyFilled(this.changeLastNameTextField)) {
				Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().setLastName(changeLastNameTextField.getText());
				String idNumber = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getIdNumber();
				String lastName = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getLastName();
				sendChangeCustomerLastName(idNumber, lastName);
				setCustomerLastNameText();
				refreshTopCustomerInterfaceData();
				setMessageText("Data changed");
			}else {
				setMessageText("Incorrect data");
			}
		});
		this.add(changeLastNameButton, 2, 1);
	}

	private void setChangePasswordButton() {
		changePassword = new Button("Change password");
		changePassword.setId("leftPanelButton");
		changePassword.setTranslateX(40);
		changePassword.setTranslateY(40);
		changePassword.setOnAction(e-> {
			if(isFieldProperlyFilled(this.changePasswordTextField)) {
				Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().setPassword(changePasswordTextField.getText());
				setCustomerPasswordText();
				String idNumber = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getIdNumber();
				String password = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getPassword();
				sendChangeCustomerPassword(idNumber, password);
				setMessageText("Data changed");
			}else {
				setMessageText("Incorrect data");
			}
		});
		this.add(changePassword, 2, 4);
	}

	public Text getCustomerPasswordText() {
		return customerPasswordText;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void sendChangeCustomerFirstName(String idNumber, String firstName) {
		try {

			ClientQuery clientQuery = new ClientQuery("changeCustomerFirstName");
			clientQuery.parameters[0] = idNumber;
			clientQuery.parameters[1] = firstName;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendChangeCustomerLastName(String idNumber, String lastName) {
		try {

			ClientQuery clientQuery = new ClientQuery("changeCustomerLastName");
			clientQuery.parameters[0] = idNumber;
			clientQuery.parameters[1] = lastName;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendChangeCustomerPassword(String idNumber, String password) {
		try {

			ClientQuery clientQuery = new ClientQuery("changeCustomerPassword");
			clientQuery.parameters[0] = idNumber;
			clientQuery.parameters[1] = password;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
