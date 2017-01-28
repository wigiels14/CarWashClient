package Business.Order;

import java.util.ArrayList;

import Business.Order.Discount.Discount;
import Business.Order.Payment.PaymentExecution;
import Business.Person.Customer;
import Business.Service.Service;
import Business.Vehicle.Vehicle;

public class Order {
	private String id;
	private Customer customer;
	private PaymentExecution paymentExecution;
	private String state;
	private double cost;
	private Service service;
	private final ArrayList<Discount> discount = new ArrayList<>();
	private Vehicle vehicle;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPayment(PaymentExecution payment) {
		paymentExecution = payment;
	}

	public PaymentExecution getPayment() {
		return paymentExecution;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Service getService() {
		return service;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

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
