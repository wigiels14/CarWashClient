package Graphic.CustmerInterface.Optional;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import Business.Service.Service;
import Business.Vehicle.Vehicle;
import Server.ClientQuery;
import Starting.Client;

public class AddOrderPanel extends GridPane {
	Text carText, serviceText;
	ChoiceBox carChoiceBox, serviceChoiceBox;

	public void initAll() {
		setCarText();
		setCarChoiceBox();
		sendFetchAllServices();

	}

	private void setCarText() {
		if (carText == null) {
			carText = new Text("Vehicle: ");
			carText.setId("dataText");
			carText.setTranslateX(20);
			carText.setTranslateY(60);
			this.add(carText, 0, 0);
		}
	}

	private void setServiceText() {
		if (serviceText == null) {
			serviceText = new Text("Service: ");
			serviceText.setId("dataText");
			serviceText.setTranslateX(20);
			serviceText.setTranslateY(60);
			this.add(serviceText, 0, 1);
		}
	}

	public void setServicesParts() {
		setServiceText();
		setServiceChoiceBox();
	}

	private void setCarChoiceBox() {
		ArrayList<Vehicle> vehicleFleet = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer().getVehicleFleet();
		carChoiceBox = new ChoiceBox<Vehicle>(
				FXCollections.observableList(vehicleFleet));
		carChoiceBox.setId("smallDataText");
		carChoiceBox.setTranslateX(20);
		carChoiceBox.setTranslateY(60);
		this.add(carChoiceBox, 1, 0);
	}

	private void sendFetchAllServices() {
		try {
			System.out.println("WYSYLA");
			ClientQuery clientQuery = new ClientQuery("fetchAllServices");
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setServiceChoiceBox() {
		if (serviceChoiceBox == null) {
			serviceChoiceBox = new ChoiceBox<Service>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getServices()));
			serviceChoiceBox.setId("smallDataText");
			serviceChoiceBox.setTranslateX(20);
			serviceChoiceBox.setTranslateY(60);
			this.add(serviceChoiceBox, 1, 1);
		} else {
			serviceChoiceBox = new ChoiceBox<Service>(
					FXCollections
							.observableList(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getServices()));
		}
	}
}
