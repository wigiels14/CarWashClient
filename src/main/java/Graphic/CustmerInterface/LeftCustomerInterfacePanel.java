package Graphic.CustmerInterface;

import java.io.IOException;

import Business.Person.Customer;
import Business.Vehicle.Vehicle;
import Server.ClientQuery;
import Starting.Client;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftCustomerInterfacePanel extends VBox {
	Button changePersonalDataButton, addVehicleButton, showVehicleFleetButton,
			showActiveOrders, showOrderHistory, addOrder;

	public LeftCustomerInterfacePanel() {
		setChangePersonalDataButton();
		setAddVehicleButton();
		setShowVehicleFleetButton();
		setShowActiveOrders();
		setShowOrderHistory();
		setAddOrder();
	}

	private void setChangePersonalDataButton() {
		System.out.println("tworze");
		changePersonalDataButton = new Button("Change personal data");
		changePersonalDataButton.setId("leftPanelButton");
		changePersonalDataButton.setTranslateX(17);
		changePersonalDataButton.setTranslateY(80);
		changePersonalDataButton.setOnAction(e -> {
			Client.mainCustomerInterfacePanel.setCenterChangePDInterfacePanel();
		});
		this.getChildren().add(changePersonalDataButton);
	}

	private void setAddVehicleButton() {
		addVehicleButton = new Button("Add vehicle");
		addVehicleButton.setId("leftPanelButton");
		addVehicleButton.setTranslateX(17);
		addVehicleButton.setTranslateY(90);
		addVehicleButton.setOnAction(e -> {
			Client.mainCustomerInterfacePanel.setCenterAddVehicleInterfacePanel();
		});
		this.getChildren().add(addVehicleButton);
	}

	private void setShowVehicleFleetButton() {
		showVehicleFleetButton = new Button("Vehicle fleet");
		showVehicleFleetButton.setId("leftPanelButton");
		showVehicleFleetButton.setTranslateX(17);
		showVehicleFleetButton.setTranslateY(100);
		showVehicleFleetButton.setOnAction(e -> {
			Client.mainCustomerInterfacePanel.setCenterShowVehicleFleetInterfacePanel();
		});
		this.getChildren().add(showVehicleFleetButton);
	}

	private void setShowActiveOrders() {
		showActiveOrders = new Button("Active orders");
		showActiveOrders.setId("leftPanelButton");
		showActiveOrders.setTranslateX(17);
		showActiveOrders.setTranslateY(110);
		showActiveOrders.setOnAction(e -> {
			Client.mainCustomerInterfacePanel.status = new String("1");
			Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer();
			Client.mainCustomerInterfacePanel.showActiveOrdersPanel.sendFetchOrdersByCustomerID(customer.getId());
		});
		this.getChildren().add(showActiveOrders);
	}

	private void setShowOrderHistory() {
		showOrderHistory = new Button("Orders history");
		showOrderHistory.setId("leftPanelButton");
		showOrderHistory.setTranslateX(17);
		showOrderHistory.setTranslateY(120);
		showOrderHistory.setOnAction(e -> {
			Client.mainCustomerInterfacePanel.status = new String("2");
			Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer();
			Client.mainCustomerInterfacePanel.showActiveOrdersPanel.sendFetchOrdersByCustomerID(customer.getId());
		});
		this.getChildren().add(showOrderHistory);
	}

	private void setAddOrder() {
		addOrder = new Button("Add order");
		addOrder.setId("leftPanelButton");
		addOrder.setTranslateX(17);
		addOrder.setTranslateY(130);
		addOrder.setOnAction(e -> {
			Client.mainCustomerInterfacePanel.setCenterAddOrderInterfacePanel();
		});
		this.getChildren().add(addOrder);
	}

	public void sendFetchCustomer() {
		try {

			ClientQuery clientQuery = new ClientQuery("fetchCustomer");
			clientQuery.parameters[0] = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
					.getCustomerIdNumber();
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
