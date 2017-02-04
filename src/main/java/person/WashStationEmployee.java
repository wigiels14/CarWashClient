package person;

public class WashStationEmployee implements PersonEntity {
	private String id, customerPassword, firstName, lastName, pesel, idNumber;

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
