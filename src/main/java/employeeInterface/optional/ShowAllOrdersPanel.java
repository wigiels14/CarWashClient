package employeeInterface.optional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import client.Client;
import client.ClientQuery;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import order.Order;
import person.Employee;

public class ShowAllOrdersPanel extends GridPane {
	ArrayList<Order> orders = new ArrayList<Order>();
	ArrayList<Text> orderDatas = new ArrayList<Text>();
	ArrayList<Button> orderButtons = new ArrayList<Button>();

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public void initAll() {
		this.getChildren().clear();
		int row = 0;
		int col = 0;

		Iterator orderIterator = orders.iterator();

		while (orderIterator.hasNext()) {
			Order order = (Order) orderIterator.next();
			setOrderDatas(col, row, order);
			col++;
			setOrderButtons(col, row, order);
			col = 0;
			row++;
		}
	}

	private void setOrderDatas(int i, int j, Order order) {
		Text orderData = new Text(
				"Service: " + order.getService().getName() + ", vehicle: " + order.getVehicle().toString());
		orderData.setId("mediumDataText");
		orderData.setTranslateX(40);
		orderData.setTranslateY(60);
		orderDatas.add(orderData);
		this.add(orderData, i, j);
	}

	private void setOrderButtons(int i, int j, Order order) {
		Button orderButton = new Button("Take the order");
		orderButton.setId("leftPanelButton");
		orderButton.setTranslateX(40);
		orderButton.setTranslateY(60);
		orderButtons.add(orderButton);
		Order sendingOrder = orders.get(j);
		orderButton.setOnAction(e -> {
			String employeeID = Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().getId();
			String orderID = sendingOrder.getId();

			sendAddEmpoyeeOrder(employeeID, orderID);
		});
		this.add(orderButton, i, j);
	}

	public void sendFetchNotTakenOrders() {
		try {
			Employee employee = Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee();
			String service = employee.getType() + "Service";
			ClientQuery clientQuery = new ClientQuery("fetchNotTakenOrders");
			clientQuery.parameters[0] = service;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendAddEmpoyeeOrder(String employeeID, String orderID) {
		try {
			ClientQuery clientQuery = new ClientQuery("addEmployeeOrder");
			clientQuery.parameters[0] = employeeID;
			clientQuery.parameters[1] = orderID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
