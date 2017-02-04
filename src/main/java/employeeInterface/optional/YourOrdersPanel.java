package employeeInterface.optional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import client.Client;
import client.ClientQuery;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import order.Order;

public class YourOrdersPanel extends GridPane {
	ArrayList<Order> orders = new ArrayList<Order>();
	ArrayList<Text> orderDatas = new ArrayList<Text>();
	ArrayList<Button> orderButtons = new ArrayList<Button>();
	ArrayList<ComboBox> statusComboBoxes = new ArrayList<ComboBox>();
	ArrayList<String> statuses = new ArrayList<>();

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public void initAll() {
		statuses.clear();
		statuses.add("active");
		statuses.add("in process");
		statuses.add("finished");

		this.getChildren().clear();
		int row = 0;
		int col = 0;

		Iterator orderIterator = orders.iterator();

		while (orderIterator.hasNext()) {
			Order order = (Order) orderIterator.next();
			setOrderDatas(col, row, order);
			col++;
			setStatusComboBoxes(col, row, order);
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

	private void setStatusComboBoxes(int i, int j, Order order) {
		ComboBox statusComboBox = new ComboBox<String>(FXCollections.observableList(statuses));
		switch (order.getState()) {
		case ("active"):
			statusComboBox.getSelectionModel().select(0);
			break;
		case ("in process"):
			statusComboBox.getSelectionModel().select(1);
			break;
		case ("finished"):
			statusComboBox.getSelectionModel().select(2);
			break;
		}
		statusComboBox.setId("smallDataText");
		statusComboBox.setTranslateX(60);
		statusComboBox.setTranslateY(60);
		statusComboBoxes.add(statusComboBox);
		this.add(statusComboBox, i, j);
	}

	private void setOrderButtons(int i, int j, Order order) {
		Button orderButton = new Button("Change");
		orderButton.setId("leftPanelButton");
		orderButton.setTranslateX(80);
		orderButton.setTranslateY(60);
		orderButtons.add(orderButton);
		Order sendingOrder = orders.get(j);
		orderButton.setOnAction(e -> {
			sendChangeOrderStatus(sendingOrder.getId(), statusComboBoxes.get(j).getValue().toString());
		});
		this.add(orderButton, i, j);
	}

	public void sendFetchEmployeeOrders() {
		try {
			String employeeID = Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().getId();
			ClientQuery clientQuery = new ClientQuery("fetchEmployeeOrders");
			clientQuery.parameters[0] = employeeID;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendChangeOrderStatus(String orderID, String status) {
		try {
			ClientQuery clientQuery = new ClientQuery("changeOrderStatus");
			clientQuery.parameters[0] = orderID;
			clientQuery.parameters[1] = status;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}