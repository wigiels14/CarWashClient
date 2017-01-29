package Graphic.CustmerInterface.Optional;

import java.io.IOException;

import Business.Person.Customer;
import Business.Vehicle.Vehicle;
import Server.ClientQuery;
import Starting.Client;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class AddVehiclePanel extends GridPane {
	private Text vehicleVINText, vehicleMarkText, vehicleModelText,
			messageText;
	private TextField vehicleVINTextField, vehicleMarkTextField,
			vehicleModelTextField;
	private Button acceptDataButton;

	public AddVehiclePanel() {
	}

	public void initAll() {
		setVehicleVINText();
		setVehicleVINTextField();
		setVehicleMarkText();
		setVehicleMarkTextField();
		setVehicleModelText();
		setVehicleModelTextField();
		setAcceptButton();
	}

	private void setVehicleVINText() {
		if (vehicleVINText == null) {
			vehicleVINText = new Text("VIN: ");
			vehicleVINText.setId("dataText");
			vehicleVINText.setTranslateX(20);
			vehicleVINText.setTranslateY(60);
			this.add(vehicleVINText, 0, 0);
		} else {
			vehicleVINText = new Text("VIN: ");
		}
	}

	private void setVehicleVINTextField() {
		vehicleVINTextField = new TextField();
		vehicleVINTextField.setTranslateX(30);
		vehicleVINTextField.setTranslateY(60);
		this.add(vehicleVINTextField, 1, 0);
	}

	private void setVehicleMarkText() {
		if (vehicleMarkText == null) {
			vehicleMarkText = new Text("Mark: ");
			vehicleMarkText.setId("dataText");
			vehicleMarkText.setTranslateX(20);
			vehicleMarkText.setTranslateY(60);
			this.add(vehicleMarkText, 0, 1);
		} else {
			vehicleMarkText = new Text("Mark: ");
		}
	}

	private void setVehicleMarkTextField() {
		vehicleMarkTextField = new TextField();
		vehicleMarkTextField.setTranslateX(30);
		vehicleMarkTextField.setTranslateY(60);
		this.add(vehicleMarkTextField, 1, 1);
	}

	private void setVehicleModelText() {
		if (vehicleModelText == null) {
			vehicleModelText = new Text("Model: ");
			vehicleModelText.setId("dataText");
			vehicleModelText.setTranslateX(20);
			vehicleModelText.setTranslateY(60);
			this.add(vehicleModelText, 0, 2);
		} else {
			vehicleModelText = new Text("Model: ");
		}
	}

	private void setVehicleModelTextField() {
		vehicleModelTextField = new TextField();
		vehicleModelTextField.setTranslateX(30);
		vehicleModelTextField.setTranslateY(60);
		this.add(vehicleModelTextField, 1, 2);
	}

	private void setAcceptButton() {
		acceptDataButton = new Button("Accept data");
		acceptDataButton.setId("leftPanelButton");
		acceptDataButton.setTranslateX(40);
		acceptDataButton.setTranslateY(100);
		acceptDataButton.setOnAction(e-> {
			if(isPanelProperlyFilled()) {
				String customerIDNumber = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomerID();
				Vehicle vehicle = new Vehicle();
				vehicle.setVin(vehicleVINTextField.getText());
				vehicle.setBrand(vehicleMarkTextField.getText());
				vehicle.setCarModel(vehicleModelTextField.getText());
				Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().addVehicle(vehicle);
				sendAddVehicle(vehicleVINTextField.getText(), vehicleMarkTextField.getText(), vehicleModelTextField.getText(), customerIDNumber);
				setMessageText("Vehicle has been added");
			}else {
				setMessageText("Incorrect data");
			}
		});
		this.add(acceptDataButton, 2, 3);
	}

	private boolean isPanelProperlyFilled() {
		if ((vehicleMarkTextField.getText().equals(""))
				|| (vehicleModelTextField.getText().equals(""))
				|| (vehicleVINTextField.getText().equals(""))) {
			return false;
		}
		return true;
	}

	private void setMessageText(String message) {
		if (messageText == null) {
			messageText = new Text(message);
			messageText.setId("dataText");
			messageText.setTranslateX(60);
			messageText.setTranslateY(100);
			this.add(messageText, 4, 3);
		} else {
			messageText.setText(message);
		}
	}

	public void sendAddVehicle(String vin, String mark, String model,
			String customerIDNumber) {
		try {

			ClientQuery clientQuery = new ClientQuery("addVehicle");
			clientQuery.parameters[0] = vin;
			clientQuery.parameters[1] = mark;
			clientQuery.parameters[2] = model;
			clientQuery.parameters[3] = customerIDNumber;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
