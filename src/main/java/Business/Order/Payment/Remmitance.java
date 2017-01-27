package Business.Order.Payment;

import java.util.Date;

import Business.Order.Order;
import Business.Person.Customer;

public class Remmitance {
	private Customer customer;
	private Order order;
	private Date date;
	private double cost;

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getCost() {
		return cost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Date getDate() {
		return date;
	}

	public Order getOrder() {
		return order;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
