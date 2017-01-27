package Graphic.CustmerInterface;

import java.util.ArrayList;

import Starting.Client;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import Business.Person.Customer;
import Business.Person.EntityFactory;
import Business.Service.Service;

public class TopCustomerInterfacePanel extends HBox {
	private Customer customer;
	private final ArrayList<Service> services = new ArrayList<Service>();
	private EntityFactory entityFactory = new EntityFactory();;
	Text idText, firstNameText, lastNameText, accountBalanceText;
	Button endAppButton;

	public ArrayList<Service> getServices() {
		return services;
	}

	public void addService(Service service) {
		this.services.add(service);
	}

	public TopCustomerInterfacePanel() {
		setCustomer();
		setEndAppButton();
	}

	private void setCustomer() {
		entityFactory = new EntityFactory();

		customer = (Customer) entityFactory.PersonEntity("customer");
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setIDText() {
		idText = new Text("ID Number: " + customer.getIdNumber());
		idText.setId("registerText");
		idText.setTranslateX(40);
		idText.setTranslateY(30);
		this.getChildren().add(idText);
	}

	public void setFirstNameText() {
		if (firstNameText == null) {
			firstNameText = new Text("First name: " + customer.getFirstName());
			firstNameText.setId("registerText");
			firstNameText.setTranslateX(70);
			firstNameText.setTranslateY(30);
			this.getChildren().add(firstNameText);
		} else {
			firstNameText.setText("First name: " + customer.getFirstName());
		}
	}

	public void setLastNameText() {
		if (lastNameText == null) {
			lastNameText = new Text("Last name: " + customer.getLastName());
			lastNameText.setId("registerText");
			lastNameText.setTranslateX(100);
			lastNameText.setTranslateY(30);
			this.getChildren().add(lastNameText);
		}
		lastNameText.setText("Last name: " + customer.getLastName());
	}

	public void accountBalaceText() {
		accountBalanceText = new Text("Account balance: "
				+ customer.getAccountBalance());
		accountBalanceText.setId("registerText");
		accountBalanceText.setTranslateX(130);
		accountBalanceText.setTranslateY(30);
		this.getChildren().add(accountBalanceText);
	}

	public void setEndAppButton() {
		endAppButton = new Button("Log out");
		endAppButton.setId("myLogInButton");
		endAppButton.setTranslateX(30);
		endAppButton.setTranslateY(30);
		endAppButton.setOnAction(e-> {
			Client.primaryStage.close();
		});
		this.getChildren().add(endAppButton);
	}

	public String getCustomerID() {
		return customer.getId();
	}

	public void setCustomerID(String id) {
		customer.setId(id);
	}

	public String getCustomerPassword() {
		return customer.getPassword();
	}

	public void setCustomerPassword(String customerPassword) {
		customer.setPassword(customerPassword);
	}

	public String getCustomerFirstName() {
		return customer.getFirstName();
	}

	public void setCustomerFirstName(String firstName) {
		customer.setFirstName(firstName);
	}

	public String getCustomerLastName() {
		return customer.getLastName();
	}

	public void setCustomerLastName(String lastName) {
		customer.setLastName(lastName);
	}

	public String getCustomerPesel() {
		return customer.getPesel();
	}

	public void setCustomerPesel(String pesel) {
		customer.setPesel(pesel);
	}

	public double getCustomerAccountBalance() {
		return customer.getAccountBalance();
	}

	public void setCustomerAccountBalance(String accountBalance) {
		customer.setAccountBalance(Double.parseDouble(accountBalance));
	}

	public String getCustomerIdNumber() {
		return customer.getIdNumber();
	}

	public void setCustomerIdNumber(String idNumber) {
		customer.setIdNumber(idNumber);
	}

}
