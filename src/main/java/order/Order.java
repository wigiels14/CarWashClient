package order;

import person.Customer;
import service.Service;
import vehicle.Vehicle;

public class Order {
	private String id;
	private Customer customer;
	private String state;
	private double cost;
	private Service service;
	private Vehicle vehicle;

	public Order(OrderBuilder orderBuilder) {
		this.id = orderBuilder.id;
		this.customer = orderBuilder.customer;
		this.state = orderBuilder.state;
		this.cost = orderBuilder.cost;
		this.service = orderBuilder.service;
		this.vehicle = orderBuilder.vehicle;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
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

	public void setVehicle(Vehicle string) {
		this.vehicle = string;
	}

	public Vehicle getVehicle() {
		return vehicle;
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

	public static class OrderBuilder {
		private String id;
		private Customer customer;
		private String state;
		private double cost;
		private Service service;
		private Vehicle vehicle;

		public OrderBuilder createVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
			return this;
		}

		public OrderBuilder createService(Service service) {
			this.service = service;
			return this;
		}

		public OrderBuilder createCost(double cost) {
			this.cost = cost;
			return this;
		}

		public OrderBuilder createState(String state) {
			this.state = state;
			return this;
		}

		public OrderBuilder createID(String id) {
			this.id = id;
			return this;
		}

		public OrderBuilder createCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

}
