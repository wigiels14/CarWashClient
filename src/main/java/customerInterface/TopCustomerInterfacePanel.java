package customerInterface;

import java.io.IOException;
import java.util.ArrayList;

import client.Client;
import client.ClientQuery;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import order.payment.CarWashCardPayment;
import order.payment.Payment;
import person.Customer;
import person.EntityFactory;
import service.Service;

public class TopCustomerInterfacePanel extends HBox {
	private Customer customer;
	private final ArrayList<Service> services = new ArrayList<Service>();
	private ArrayList<Service> serviceOrders = new ArrayList<Service>();
	private final ArrayList<Payment> payments = new ArrayList<Payment>();
	private EntityFactory entityFactory = new EntityFactory();
	Text idText, firstNameText, lastNameText, accountBalanceText;
	Button endAppButton;

	public void setServiceOrders(ArrayList<Service> serviceOrders) {
		this.serviceOrders = serviceOrders;
	}

	public ArrayList<Service> getServiceOrders() {
		return serviceOrders;
	}

	public ArrayList<Payment> getPayments() {
		return payments;
	}

	public void addPayment(Payment payment) {
		this.payments.add(payment);
	}

	public ArrayList<Service> getServices() {
		return services;
	}

	public void addService(Service service) {
		this.services.add(service);
	}

	public TopCustomerInterfacePanel() {
		addPayment(new CarWashCardPayment());
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
		idText.setTranslateX(70);
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
		if (accountBalanceText == null) {
			accountBalanceText = new Text("Account balance: " + customer.getAccountBalance());
			accountBalanceText.setId("registerText");
			accountBalanceText.setTranslateX(130);
			accountBalanceText.setTranslateY(30);
			this.getChildren().add(accountBalanceText);
		} else {
			accountBalanceText.setText("Account balance: " + customer.getAccountBalance());
		}
	}

	public void setEndAppButton() {
		endAppButton = new Button("Log out");
		endAppButton.setId("myLogInButton");
		endAppButton.setTranslateX(30);
		endAppButton.setTranslateY(30);
		endAppButton.setOnAction(e -> {
			sendExitApp();
			Client.primaryStage.close();
			System.exit(0);
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

	public void sendExitApp() {
		try {

			ClientQuery clientQuery = new ClientQuery("sendExitApp");
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
