package Starting;

import java.io.IOException;

import Graphic.CustmerInterface.TopCustomerInterfacePanel;
import Server.ClientQuery;
import Server.Server;
import javafx.application.Platform;

public class ServerCommunicationThread extends Thread {

	public void run() {
		while (true) {
			Object response = convertServerResponce();
			System.out.println(response);

			if (response instanceof ClientQuery) {
				if (((ClientQuery) response).type
						.equals("isUserInSystemDatabase")) {
					proceedIsUserInSystemDatabaseResponce((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("createCustomer")) {
					proceedCreateCustomer((ClientQuery) response);
				}
				if (((ClientQuery) response).type
						.equals("isCustomerAlreadyRegistered")) {
					proceedIsCustomerAlreadyRegistered((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchCustomer")) {
					proceedFetchCustomer((ClientQuery) response);
				}
			}
		}
	}

	private void proceedFetchCustomer(ClientQuery response) {
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerID(response.parameters[0]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerPassword(response.parameters[1]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerFirstName(response.parameters[2]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerLastName(response.parameters[3]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerPesel(response.parameters[4]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerIdNumber(response.parameters[5]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerAccountBalance(response.parameters[6]);

		Platform.runLater(() ->Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setIDText());
		Platform.runLater(() ->Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setFirstNameText());
		Platform.runLater(() ->Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setLastNameText());
		Platform.runLater(() ->Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.accountBalaceText());

		System.out.println(response.getAdditionalObjects().toString());
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.setTopCustomerInterfacePanel());
	}

	private void setCustmerInterfaceSceneActive() {
		Platform.runLater(() -> Client.setCustmerInterfaceSceneActive());
	}

	private void fetchCustomerFromDatabase(String idNumber) {
		try {
			ClientQuery clientQuery = new ClientQuery("fetchCustomer");
			clientQuery.parameters[0] = idNumber;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void proceedIsUserInSystemDatabaseResponce(ClientQuery response) {
		if (response.isResponseTrue) {
			fetchCustomerFromDatabase(response.parameters[0]);
			setCustmerInterfaceSceneActive();
		} else {
			Client.mainLogInPanel.myVBox.setWrongDataField("Invalid data");
		}
	}

	private void proceedCreateCustomer(ClientQuery responce) {
		if (responce.isResponseTrue) {
			Client.registerPanel.setUserAdded();
		}
	}

	private void proceedIsCustomerAlreadyRegistered(ClientQuery responce) {
		if (responce.isResponseTrue) {
			Client.registerPanel.setUserAlrExist();
		} else {
			Client.registerPanel.sendCreateCustomer();
			Client.registerPanel.setUserAdded();
		}
	}

	private Object convertServerResponce() {
		Object response = null;
		try {
			response = Client.in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
