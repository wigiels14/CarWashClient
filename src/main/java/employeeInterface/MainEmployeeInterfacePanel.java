package employeeInterface;

import employeeInterface.optional.ShowAllOrdersPanel;
import employeeInterface.optional.YourOrdersPanel;
import javafx.scene.layout.BorderPane;

public class MainEmployeeInterfacePanel extends BorderPane {
	public TopEmployeeInterfacePanel topEmployeeInterfacePanel;
	public LeftEmployeeInterfacePanel leftEmployeeInterfacePanel;
	public ShowAllOrdersPanel showAllOrdersPanel;
	public YourOrdersPanel yourOrdersPanel;

	public MainEmployeeInterfacePanel() {
		topEmployeeInterfacePanel = new TopEmployeeInterfacePanel();
		leftEmployeeInterfacePanel = new LeftEmployeeInterfacePanel();
		showAllOrdersPanel = new ShowAllOrdersPanel();
		yourOrdersPanel = new YourOrdersPanel();

		setLeftEmployeeInterfacePanel();
	}

	public void setTopEmployeeInterfacePanel() {
		this.setTop(topEmployeeInterfacePanel);
	}

	public void setCenterShowAllOrdersPanel() {
		showAllOrdersPanel.initAll();
		this.setCenter(showAllOrdersPanel);
	}

	public void setCenterYourOrdersPanel() {
		yourOrdersPanel.initAll();
		this.setCenter(yourOrdersPanel);
	}

	public void setLeftEmployeeInterfacePanel() {
		this.setLeft(leftEmployeeInterfacePanel);
	}

}