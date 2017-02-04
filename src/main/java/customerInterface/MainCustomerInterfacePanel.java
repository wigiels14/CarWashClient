package customerInterface;

import customerInterface.optional.AddOrderPanel;
import customerInterface.optional.AddVehiclePanel;
import customerInterface.optional.ChangePersonalDataPanel;
import customerInterface.optional.ShowActiveOrdersPanel;
import customerInterface.optional.ShowOrderHistory;
import customerInterface.optional.ShowVehicleFleetPane;
import javafx.scene.layout.BorderPane;

public class MainCustomerInterfacePanel extends BorderPane {
	public TopCustomerInterfacePanel topCustomerInterfacePanel;
	public ChangePersonalDataPanel changePersonalDataPanel;
	public LeftCustomerInterfacePanel leftCustomerInterfacePanel;
	public AddVehiclePanel addVehiclePanel;
	public ShowVehicleFleetPane showVehicleFleetPane;
	public AddOrderPanel addOrderPanel;
	public ShowActiveOrdersPanel showActiveOrdersPanel;
	public ShowOrderHistory showOrderHistory;
	public String status;

	public MainCustomerInterfacePanel() {
		leftCustomerInterfacePanel = new LeftCustomerInterfacePanel();
		changePersonalDataPanel = new ChangePersonalDataPanel();
		topCustomerInterfacePanel = new TopCustomerInterfacePanel();
		addVehiclePanel = new AddVehiclePanel();
		showVehicleFleetPane = new ShowVehicleFleetPane();
		addOrderPanel = new AddOrderPanel();
		showActiveOrdersPanel = new ShowActiveOrdersPanel();
		showOrderHistory = new ShowOrderHistory();

		addOrderPanel.sendFetchAllServices();

		setLeftCustomerInterfacePanel();
	}

	public void setLeftCustomerInterfacePanel() {
		this.setLeft(leftCustomerInterfacePanel);
	}

	public void setTopCustomerInterfacePanel() {
		this.setTop(topCustomerInterfacePanel);
	}

	public void setCenterChangePDInterfacePanel() {
		changePersonalDataPanel.setCustomer(topCustomerInterfacePanel.getCustomer());
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

	public void setCenterShowActiveOrdersInterfacePanel() {
		if (status.equals("1")) {
			showActiveOrdersPanel.initAll();
			this.setCenter(showActiveOrdersPanel);
		} else {
			showOrderHistory.initAll();
			this.setCenter(showOrderHistory);
		}
	}

}
