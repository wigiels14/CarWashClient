package Graphic.CustmerInterface;

import javafx.scene.layout.BorderPane;
import Graphic.CustmerInterface.Optional.AddOrderPanel;
import Graphic.CustmerInterface.Optional.AddVehiclePanel;
import Graphic.CustmerInterface.Optional.ChangePersonalDataPanel;
import Graphic.CustmerInterface.Optional.ShowVehicleFleetPane;

public class MainCustomerInterfacePanel extends BorderPane {
	public TopCustomerInterfacePanel topCustomerInterfacePanel;
	public ChangePersonalDataPanel changePersonalDataPanel;
	public LeftCustomerInterfacePanel leftCustomerInterfacePanel;
	public AddVehiclePanel addVehiclePanel;
	public ShowVehicleFleetPane showVehicleFleetPane;
	public AddOrderPanel addOrderPanel;

	public MainCustomerInterfacePanel() {
		leftCustomerInterfacePanel = new LeftCustomerInterfacePanel();
		changePersonalDataPanel = new ChangePersonalDataPanel();
		topCustomerInterfacePanel = new TopCustomerInterfacePanel();
		addVehiclePanel = new AddVehiclePanel();
		showVehicleFleetPane = new ShowVehicleFleetPane();
		addOrderPanel = new AddOrderPanel();
		setStyle("-fx-background-image: url('tlo.png');"
				+ "-fx-background-radius: 50px;");

		setLeftCustomerInterfacePanel();
	}

	public void setLeftCustomerInterfacePanel() {
		this.setLeft(leftCustomerInterfacePanel);
	}

	public void setTopCustomerInterfacePanel() {
		this.setTop(topCustomerInterfacePanel);
	}

	public void setCenterChangePDInterfacePanel() {
		changePersonalDataPanel.setCustomer(topCustomerInterfacePanel
				.getCustomer());
		changePersonalDataPanel.initAll();
		this.setCenter(changePersonalDataPanel);
	}

	public void setCenterAddVehicleInterfacePanel() {
		addVehiclePanel.initAll();
		this.setCenter(addVehiclePanel);
	}

	public void setCenterShowVehicleFleetInterfacePanel() {
		showVehicleFleetPane.initAll();
		this.setCenter(showVehicleFleetPane);
	}

	public void setCenterAddOrderInterfacePanel() {
		addOrderPanel.initAll();
		this.setCenter(addOrderPanel);
	}
}
