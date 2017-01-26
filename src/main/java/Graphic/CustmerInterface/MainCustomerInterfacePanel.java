package Graphic.CustmerInterface;

import javafx.scene.layout.BorderPane;
import Graphic.CustmerInterface.Optional.ChangePersonalDataPanel;

public class MainCustomerInterfacePanel extends BorderPane {
	public TopCustomerInterfacePanel topCustomerInterfacePanel;
	public ChangePersonalDataPanel changePersonalDataPanel;
	public LeftCustomerInterfacePanel leftCustomerInterfacePanel;

	public MainCustomerInterfacePanel() {
		leftCustomerInterfacePanel = new LeftCustomerInterfacePanel();
		changePersonalDataPanel = new ChangePersonalDataPanel();
		topCustomerInterfacePanel = new TopCustomerInterfacePanel();
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

	public void setCenterInterfacePanel() {
		changePersonalDataPanel.setCustomer(topCustomerInterfacePanel
				.getCustomer());
		changePersonalDataPanel.initAll();
		this.setCenter(changePersonalDataPanel);
	}
}
