package order.payment;

import order.Order;

public class Remmitance {
	private String id;
	private String payment;
	private Order order;

	public void setId(String id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public String getPayment() {
		return payment;
	}
}
