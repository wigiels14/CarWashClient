package Business.Person;

import java.util.ArrayList;

import Business.Vehicle.Vehicle;

public class Customer implements PersonEntity {
	private String id, customerPassword, firstName, lastName, pesel, idNumber;
	private double accountBalance;
	private final ArrayList<Vehicle> vehicleFleet = new ArrayList<Vehicle>();

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

	public String getPassword() {
		return customerPassword;
	}

	public void setPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getId() {
		return id;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
