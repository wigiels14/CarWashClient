package person;

import java.util.ArrayList;

import order.Order;
import vehicle.Vehicle;

public class Customer implements PersonEntity {
	private String id, customerPassword, firstName, lastName, pesel, idNumber;
	private double accountBalance;
	private ArrayList<Order> orders = new ArrayList<Order>();
	private final ArrayList<Vehicle> vehicleFleet = new ArrayList<Vehicle>();

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public ArrayList<Vehicle> getVehicleFleet() {
		return vehicleFleet;
	}

	public void addVehicle(Vehicle vehicle) {
		vehicleFleet.add(vehicle);
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String getPassword() {
		return customerPassword;
	}

	@Override
	public void setPassword(String customerPassword) {
		this.customerPassword = customerPassword;
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
