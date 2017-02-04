package employeeInterface;

import java.io.IOException;

import client.Client;
import client.ClientQuery;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import person.Employee;
import person.EntityFactory;

public class TopEmployeeInterfacePanel extends HBox {
	private Employee employee;
	private EntityFactory entityFactory = new EntityFactory();
	Text idText, firstNameText, lastNameText, typeText;
	Button endAppButton;

	public TopEmployeeInterfacePanel() {
		setEmployee();
		setEndAppButton();
	}

	private void setEmployee() {
		entityFactory = new EntityFactory();

		employee = (Employee) entityFactory.PersonEntity("employee");
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setIDText() {
		idText = new Text("ID Number: " + employee.getIdNumber());
		idText.setId("registerText");
		idText.setTranslateX(70);
		idText.setTranslateY(30);
		this.getChildren().add(idText);
	}

	public void setFirstNameText() {
		if (firstNameText == null) {
			firstNameText = new Text("First name: " + employee.getFirstName());
			firstNameText.setId("registerText");
			firstNameText.setTranslateX(90);
			firstNameText.setTranslateY(30);
			this.getChildren().add(firstNameText);
		} else {
			firstNameText.setText("First name: " + employee.getFirstName());
		}
	}

	public void setLastNameText() {
		if (lastNameText == null) {
			lastNameText = new Text("Last name: " + employee.getLastName());
			lastNameText.setId("registerText");
			lastNameText.setTranslateX(110);
			lastNameText.setTranslateY(30);
			this.getChildren().add(lastNameText);
		}
		lastNameText.setText("Last name: " + employee.getLastName());
	}

	public void setTypeText() {
		if (typeText == null) {
			typeText = new Text("Type: " + employee.getType());
			typeText.setId("registerText");
			typeText.setTranslateX(140);
			typeText.setTranslateY(30);
			this.getChildren().add(typeText);
		}
		lastNameText.setText("Last name: " + employee.getLastName());
	}

	public void setEndAppButton() {
		endAppButton = new Button("Log out");
		endAppButton.setId("myLogInButton");
		endAppButton.setTranslateX(30);
		endAppButton.setTranslateY(30);
		endAppButton.setOnAction(e -> {
			sendExitApp();
			Client.primaryStage.close();
			System.exit(0);
		});
		this.getChildren().add(endAppButton);
	}

	public void sendExitApp() {
		try {

			ClientQuery clientQuery = new ClientQuery("sendExitApp");
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
