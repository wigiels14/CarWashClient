package Starting;

import java.io.IOException;

import Server.ClientQuery;

public class ServerCommunicationThread extends Thread {

	public void run() {
		while (true) {
			Object responce = convertServerResponce();
			System.out.println(responce);

			if (responce instanceof ClientQuery) {
				if (((ClientQuery) responce).type
						.equals("isUserInSystemDatabase")) {
					proceedIsUserInSystemDatabaseResponce((ClientQuery) responce);
				}
				if (((ClientQuery) responce).type.equals("createCustomer")) {
					proceedCreateCustomer((ClientQuery) responce);
				}
				if (((ClientQuery) responce).type
						.equals("isCustomerAlreadyRegistered")) {
					proceedIsCustomerAlreadyRegistered((ClientQuery) responce);
				}
			}
		}
	}

	private void proceedIsUserInSystemDatabaseResponce(ClientQuery response) {
		if (response.isResponseTrue) {
			JavaFX.mainLogInPanel.myVBox.setWrongDataField("Logged in");
		} else {
			JavaFX.mainLogInPanel.myVBox.setWrongDataField("Invalid data");
		}
	}

	private void proceedCreateCustomer(ClientQuery responce) {
		if (responce.isResponseTrue) {
			JavaFX.registerPanel.setUserAdded();
		}
	}

	private void proceedIsCustomerAlreadyRegistered(ClientQuery responce) {
		if (responce.isResponseTrue) {
			JavaFX.registerPanel.setUserAlrExist();
		} else {
			JavaFX.registerPanel.sendCreateCustomer();
			JavaFX.registerPanel.setUserAdded();
		}
	}

	private Object convertServerResponce() {
		Object response = null;
		try {
			response = JavaFX.in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
