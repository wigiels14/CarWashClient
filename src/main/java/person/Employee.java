package person;

import java.util.ArrayList;

import order.Order;

public class Employee implements PersonEntity {

	private String id, password, firstName, lastName, pesel, idNumber, type;
	private ArrayList<Order> orders = new ArrayList<Order>();

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String customerPassword) {
		this.password = customerPassword;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getIdNumber() {
		return idNumber;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getPesel() {
		return pesel;
	}

	@Override
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}