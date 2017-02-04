package vehicle;

import java.io.Serializable;

public class Vehicle implements Serializable {
	private String id;
	private String vin;
	private String brand;
	private String carModel;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public String getCarModel() {
		return carModel;
	}

	public String getId() {
		return id;
	}

	public void setMark(String brand) {
		this.brand = brand;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VIN: " + vin + ", Mark: " + brand + ", Model: " + carModel;
	}
}
