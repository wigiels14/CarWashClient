package Business.Person;


public class WashStationEmployee implements PersonEntity {
	private String id, customerPassword, firstName, lastName, pesel, idNumber;

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
