package Graphic.CustmerInterface.Optional;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Business.Person.Customer;
import Business.Vehicle.Vehicle;
import Starting.Client;

public class ShowVehicleFleetPane extends GridPane {
	ArrayList<Text> vinTexts = new ArrayList<Text>();
	ArrayList<Text> markTexts = new ArrayList<Text>();
	ArrayList<Text> modelTexts = new ArrayList<Text>();

	public void initAll() {
		this.getChildren().clear();
		Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer();
		int row = 0;
		int col = 0;
		System.out.println("vehicles: " + customer.getVehicleFleet().size());
		for (Vehicle vehicle : customer.getVehicleFleet()) {
			setVinText(col, row, vehicle);
			col++;
			setMarkTexts(col, row, vehicle);
			col++;
			setModelTexts(col, row, vehicle);
			col = 0;
			row++;
		}
	}

	private void setVinText(int i, int j, Vehicle vehicle) {
		Text vinText = new Text("VIN: " + vehicle.getVin());
		vinText.setId("dataText");
		vinText.setTranslateX(20);
		vinText.setTranslateY(60);
		vinTexts.add(vinText);
		this.add(vinText, i, j);
	}

	private void setMarkTexts(int i, int j, Vehicle vehicle) {
		Text vinText = new Text("Mark: " + vehicle.getBrand());
		vinText.setId("dataText");
		vinText.setTranslateX(40);
		vinText.setTranslateY(60);
		vinTexts.add(vinText);
		this.add(vinText, i, j);
	}

	private void setModelTexts(int i, int j, Vehicle vehicle) {
		Text vinText = new Text("Model: " + vehicle.getCarModel());
		vinText.setId("dataText");
		vinText.setTranslateX(60);
		vinText.setTranslateY(60);
		vinTexts.add(vinText);
		this.add(vinText, i, j);
	}
}
