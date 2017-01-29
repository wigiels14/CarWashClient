package Graphic.CustmerInterface.Optional;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Business.Order.Order;
import Business.Order.Payment.CarWashCardPaymentExecution;
import Business.Order.Payment.Payment;
import Business.Order.Payment.Remmitance;
import Business.Service.Service;
import Business.Vehicle.Vehicle;
import Server.ClientQuery;
import javafx.scene.control.ComboBox;
import Starting.Client;

public class AddOrderPanel extends GridPane {
	Text carText, serviceText, paymentText, message;
	private CarWashCardPaymentExecution carWashCardPaymentExecution;
	Button acceptButton;
	ComboBox carChoiceBox, serviceChoiceBox, paymentChoiceBox;

	public void setCarWashCardPaymentExecution(
			CarWashCardPaymentExecution carWashCardPaymentExecution) {
		this.carWashCardPaymentExecution = carWashCardPaymentExecution;
	}

	public CarWashCardPaymentExecution getCarWashCardPaymentExecution() {
		return carWashCardPaymentExecution;
	}

	public void initAll() {
		createCorrectDataText("");
		setCarText();
		setCarChoiceBox();
		setPaymentText();
		setPaymentChoiceBox();
		createSingUpButton();

	}

	public Order createOrder() {
		Order order = new Order.OrderBuilder()
				.createCustomer(
						Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
								.getCustomer())
				.createCost(((Service) serviceChoiceBox.getValue()).getCost())
				.createService((Service) serviceChoiceBox.getValue())
				.createVehicle((Vehicle) carChoiceBox.getValue())
				.createState("Active").build();
		return order;
	}

	private void createPaymentExecution(Order order) {
		carWashCardPaymentExecution = new CarWashCardPaymentExecution();
		carWashCardPaymentExecution.setPayment((Payment) paymentChoiceBox
				.getValue());
		carWashCardPaymentExecution
				.setCustomer(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
						.getCustomer());
		carWashCardPaymentExecution.setOrder(order);
	}

	private void setCarText() {
		if (carText == null) {
			carText = new Text("Vehicle: ");
			carText.setId("dataText");
			carText.setTranslateX(20);
			carText.setTranslateY(60);
			this.add(carText, 0, 0);
		}
	}

	private void setServiceText() {
		if (serviceText == null) {
			serviceText = new Text("Service: ");
			serviceText.setId("dataText");
			serviceText.setTranslateX(20);
			serviceText.setTranslateY(60);
			this.add(serviceText, 0, 1);
		}
	}

	private void setPaymentText() {
		if (paymentText == null) {
			paymentText = new Text("Payment: ");
			paymentText.setId("dataText");
			paymentText.setTranslateX(20);
			paymentText.setTranslateY(60);
			this.add(paymentText, 0, 2);
		}
	}

	public void setServicesParts() {
		setServiceText();
		setServiceChoiceBox();
	}

	private void setPaymentChoiceBox() {
		if (paymentChoiceBox == null) {
			paymentChoiceBox = new ComboBox<Payment>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getPayments()));
			paymentChoiceBox.setId("smallDataText");
			paymentChoiceBox.setTranslateX(20);
			paymentChoiceBox.setTranslateY(60);
			this.add(paymentChoiceBox, 1, 2);
		}
	}

	private void setCarChoiceBox() {
		ArrayList<Vehicle> vehicleFleet = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer().getVehicleFleet();
		if (carChoiceBox == null) {
			carChoiceBox = new ComboBox<Vehicle>(
					FXCollections.observableList(vehicleFleet));
			carChoiceBox.setId("smallDataText");
			carChoiceBox.setTranslateX(20);
			carChoiceBox.setTranslateY(60);
			this.add(carChoiceBox, 1, 0);
		}
	}

	public void sendFetchAllServices() {
		try {
			ClientQuery clientQuery = new ClientQuery("fetchAllServices");
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setServiceChoiceBox() {
		if (serviceChoiceBox == null) {
			serviceChoiceBox = new ComboBox<Service>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getServices()));
			serviceChoiceBox.setId("smallDataText");
			serviceChoiceBox.setTranslateX(20);
			serviceChoiceBox.setTranslateY(60);
			this.add(serviceChoiceBox, 1, 1);
		}
	}

	private void createSingUpButton() {
		acceptButton = new Button("Realize order");
		acceptButton.setId("mySignUpButton");
		acceptButton.setTranslateX(320);
		acceptButton.setTranslateY(120);

		acceptButton.setOnAction(e -> {
			createPaymentExecution(createOrder());
			if(carWashCardPaymentExecution.executePayment()) {
				String vehicleID = ((Vehicle)carChoiceBox.getValue()).getId();
				sendCreateOrder(vehicleID);
				paymentChoiceBox.disableProperty();
				
				createCorrectDataText("Operacja has been realized");
			}else {
				createCorrectDataText("Operacja could not be realized");
			}
		});
		this.add(acceptButton, 1, 3);
	}

	public void sendCreateServiceOrders() {
		String serviceID = ((Service) serviceChoiceBox.getValue()).getId();
		String orderID = carWashCardPaymentExecution.getOrder().getId();

		try {

			ClientQuery clientQuery = new ClientQuery("createServiceOrders");
			clientQuery.parameters[0] = serviceID;
			clientQuery.parameters[1] = orderID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendFetchVehicleID(String vehicleVIN) {
		try {

			ClientQuery clientQuery = new ClientQuery("fetchVehicleID");
			clientQuery.parameters[1] = vehicleVIN;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendFetchOrderID(String vehicleID) {
		try {

			ClientQuery clientQuery = new ClientQuery("fetchOrderID");
			clientQuery.parameters[0] = vehicleID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendCreateOrder(String vehicleID) {
		try {

			ClientQuery clientQuery = new ClientQuery("createOrder");
			clientQuery.parameters[0] = vehicleID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sendFetchVehicleID(((Vehicle) carChoiceBox.getValue()).getVin());
	}

	private void createCorrectDataText(String text) {
		if (message == null) {
			message = new Text("");
			message.setId("dataText");
			message.setTranslateX(170);
			message.setTranslateY(91);
			this.add(message, 1, 3);
		} else {
			message.setText(text);
		}
	}

}
