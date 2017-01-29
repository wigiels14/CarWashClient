package Graphic.CustmerInterface.Optional;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Business.Order.Order;
import Business.Order.OrderRepository;
import Business.Order.Iterator.OrderIterator;
import Business.Order.Iterator.OrderIteratorType;
import Business.Person.Customer;
import Starting.Client;

public class ShowOrderHistory extends GridPane {
	ArrayList<Text> orderStatusTexts = new ArrayList<Text>();
	ArrayList<Text> serviceNames = new ArrayList<Text>();
	ArrayList<Text> carVINs = new ArrayList<Text>();

	public void initAll() {
		this.getChildren().clear();
		int row = 0;
		int col = 0;
		Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer();

		ArrayList<Order> orders = customer.getOrders();
		OrderRepository orderRepository = new OrderRepository();

		OrderIterator orderIterator = (OrderIterator) orderRepository
				.getIterator(OrderIteratorType.FINISHED, orders);

		while (orderIterator.hasNext()) {
			Order order = (Order) orderIterator.next();
			System.out.println("SDAD: " + order.getId());
			setServiceName(col, row, order);
			col++;
			setOrderStatusTexts(col, row, order);
			col++;
			setCarVINText(col, row, order);
			col = 0;
			row++;
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

}
