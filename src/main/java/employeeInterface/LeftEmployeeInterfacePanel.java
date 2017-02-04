package employeeInterface;

import client.Client;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftEmployeeInterfacePanel extends VBox {
	Button showAllOrdersButton, manageYourOrdersButton;

	public LeftEmployeeInterfacePanel() {
		setShowAllOrdersButton();
		setManageYourOrdersButton();

	}

	private void setShowAllOrdersButton() {
		showAllOrdersButton = new Button("All orders");
		showAllOrdersButton.setId("leftPanelButton");
		showAllOrdersButton.setTranslateX(17);
		showAllOrdersButton.setTranslateY(80);
		showAllOrdersButton.setOnAction(e -> {
			Client.mainEmployeeInterfacePanel.showAllOrdersPanel.sendFetchNotTakenOrders();
		});
		this.getChildren().add(showAllOrdersButton);
	}

	private void setManageYourOrdersButton() {
		manageYourOrdersButton = new Button("Your orders");
		manageYourOrdersButton.setId("leftPanelButton");
		manageYourOrdersButton.setTranslateX(17);
		manageYourOrdersButton.setTranslateY(90);
		manageYourOrdersButton.setOnAction(e -> {
			Client.mainEmployeeInterfacePanel.yourOrdersPanel.sendFetchEmployeeOrders();
		});
		this.getChildren().add(manageYourOrdersButton);
	}
}