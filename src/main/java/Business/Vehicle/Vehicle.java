package Business.Vehicle;

public class Vehicle {
	private String id;
	private String brand;
	private String carModel;

	public String getBrand() {
		return brand;
	}

	public String getCarModel() {
		return carModel;
	}

	public String getId() {
		return id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public void setId(String id) {
		this.id = id;
	}

}
