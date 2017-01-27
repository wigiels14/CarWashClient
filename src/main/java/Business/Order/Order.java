package Business.Order;

import java.util.ArrayList;

import Business.Order.Discount.Discount;
import Business.Person.Customer;

public class Order {
	private Customer customer;
	private double cost;
	private final ArrayList<Discount> discount = new ArrayList<>();

	public ArrayList<Discount> getDiscount() {
		return discount;
	}

	public void addDiscount(Discount discount) {
		this.discount.add(discount);
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
