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
import Starting.Client;

public class AddOrderPanel extends GridPane {
	Text carText, serviceText, paymentText, message;
	private CarWashCardPaymentExecution carWashCardPaymentExecution;
	Button acceptButton;
	ChoiceBox carChoiceBox, serviceChoiceBox, paymentChoiceBox;

	public void setCarWashCardPaymentExecution(
			CarWashCardPaymentExecution carWashCardPaymentExecution) {
		this.carWashCardPaymentExecution = carWashCardPaymentExecution;
	}

	public CarWashCardPaymentExecution getCarWashCardPaymentExecution() {
		return carWashCardPaymentExecution;
	}

	public void initAll() {
		createCorrectDataText();
		setCarText();
		setCarChoiceBox();
		setPaymentText();
		setPaymentChoiceBox();
		sendFetchAllServices();
		createSingUpButton();

	}

	public Order createOrder() {
		Order order = new Order();
		order.setCustomer(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer());
		order.setCost(((Service) serviceChoiceBox.getValue()).getCost());
		order.setService((Service) serviceChoiceBox.getValue());
		order.setVehicle((Vehicle) carChoiceBox.getValue());
		order.setState("Active");
		return order;
	}

	private void createPaymentExecution(Order order) {
		carWashCardPaymentExecution = new CarWashCardPaymentExecution();
		carWashCardPaymentExecution.setPayment((Payment) paymentChoiceBox
				.getValue());
		carWashCardPaymentExecution.setOrder(order);
	}

	private void createRemmitance(Order order) {
		Remmitance remmitance = new Remmitance();
		remmitance.setOrder(order);
		remmitance.setPayment(((Payment) paymentChoiceBox.getValue())
				.toString());
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
			paymentChoiceBox = new ChoiceBox<Payment>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getPayments()));
			paymentChoiceBox.setId("smallDataText");
			paymentChoiceBox.setTranslateX(20);
			paymentChoiceBox.setTranslateY(60);
			this.add(paymentChoiceBox, 1, 2);
		} else {
			paymentChoiceBox = new ChoiceBox<Payment>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getPayments()));
		}
	}

	private void setCarChoiceBox() {
		ArrayList<Vehicle> vehicleFleet = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer().getVehicleFleet();
		if (carChoiceBox == null) {
			carChoiceBox = new ChoiceBox<Vehicle>(
					FXCollections.observableList(vehicleFleet));
			carChoiceBox.setId("smallDataText");
			carChoiceBox.setTranslateX(20);
			carChoiceBox.setTranslateY(60);
			this.add(carChoiceBox, 1, 0);
		} else {
			carChoiceBox = new ChoiceBox<Vehicle>(
					FXCollections.observableList(vehicleFleet));
		}
	}

	private void sendFetchAllServices() {
		try {
			System.out.println("WYSYLA");
			ClientQuery clientQuery = new ClientQuery("fetchAllServices");
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setServiceChoiceBox() {
		if (serviceChoiceBox == null) {
			serviceChoiceBox = new ChoiceBox<Service>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getServices()));
			serviceChoiceBox.setId("smallDataText");
			serviceChoiceBox.setTranslateX(20);
			serviceChoiceBox.setTranslateY(60);
			this.add(serviceChoiceBox, 1, 1);
		} else {
			serviceChoiceBox = new ChoiceBox<Service>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getServices()));
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
				message.setText("Operation realized");
			}else {
				message.setText("Operacja could not be realized");
			}
		});
		this.add(acceptButton, 1, 3);
	}

	private void createCorrectDataText() {
		message = new Text("");
		message.setId("wrongDataText");
		message.setTranslateX(170);
		message.setTranslateY(91);
		this.add(message, 1, 3);
	}

}
