package Graphic.CustmerInterface.Optional;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Business.Order.Order;
import Business.Person.Customer;
import Server.ClientQuery;
import Starting.Client;

public class ShowActiveOrdersPanel extends GridPane {
	ArrayList<Text> orderStatusTexts = new ArrayList<Text>();
	ArrayList<Text> serviceNames = new ArrayList<Text>();
	ArrayList<Text> carVINs = new ArrayList<Text>();

	public void initAll() {
		this.getChildren().clear();
		int row = 0;
		int col = 0;
		Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer();
		for (Order order : customer.getOrders()) {
			if (order.getState().equals("active")
					|| order.getState().equals("in process")) {
				setServiceName(col, row, order);
				col++;
				setOrderStatusTexts(col, row, order);
				col++;
				setCarVINText(col, row, order);
				col = 0;
				row++;
			}
		}
	}

	private void setOrderStatusTexts(int i, int j, Order order) {
		Text orderStatusText = new Text("Order status: " + order.getState());
		orderStatusText.setId("dataText");
		orderStatusText.setTranslateX(40);
		orderStatusText.setTranslateY(60);
		orderStatusTexts.add(orderStatusText);
		this.add(orderStatusText, i, j);
	}

	private void setServiceName(int i, int j, Order order) {
		Text serviceName = new Text("Service: " + order.getService().getName());
		serviceName.setId("dataText");
		serviceName.setTranslateX(20);
		serviceName.setTranslateY(60);
		serviceNames.add(serviceName);
		this.add(serviceName, i, j);
	}

	private void setCarVINText(int i, int j, Order order) {
		Text carVIN = new Text("Car's VIN: " + order.getVehicle().getVin());
		carVIN.setId("dataText");
		carVIN.setTranslateX(80);
		carVIN.setTranslateY(60);
		carVINs.add(carVIN);
		this.add(carVIN, i, j);
	}

	public void sendFetchOrdersByCustomerID(String customerID) {
		try {

			ClientQuery clientQuery = new ClientQuery("fetchOrdersByCustomerID");
			clientQuery.parameters[0] = customerID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendFetchServiceOrdersByCustomerID(String customerID) {
		try {
			ClientQuery clientQuery = new ClientQuery(
					"fetchServiceOrdersByCustomerID");
			clientQuery.parameters[0] = customerID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
