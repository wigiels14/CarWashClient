package Server;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class ConnectedClient extends Thread {
	Socket socket;
	ServerSocket serverSocket;
	Connection conn;
	ObjectOutputStream out;
	ObjectInputStream in;
	DataOutputStream userNumberStream;

	public ConnectedClient(Connection conn, ServerSocket serverSocket,
			Socket socket) {
		this.conn = conn;
		this.serverSocket = serverSocket;
		this.socket = socket;
	}

	void initStreams() {
		OutputStream outPut;
		try {
			outPut = socket.getOutputStream();
			out = new ObjectOutputStream(outPut);

			BufferedInputStream inPut = new BufferedInputStream(
					socket.getInputStream());
			in = new ObjectInputStream(inPut);

			userNumberStream = new DataOutputStream(socket.getOutputStream());
			System.out.print("");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		initStreams();
		System.out.println("Connected: " + socket.getInetAddress() + " :"
				+ socket.getPort());

		while (socket.isConnected()) {
			Object clientQuery = convertClientQuery();
			if (clientQuery instanceof ClientQuery) {
				if (((ClientQuery) clientQuery).type
						.equals("isUserInSystemDatabase")) {
					proceedIsUserInSystemDatabase((ClientQuery) clientQuery);
				}
			}
			if (clientQuery instanceof ClientQuery) {
				if (((ClientQuery) clientQuery).type.equals("createCustomer")) {
					proceedCreateCustomer((ClientQuery) clientQuery);
				}
			}
			if (clientQuery instanceof ClientQuery) {
				if (((ClientQuery) clientQuery).type
						.equals("isCustomerAlreadyRegistered")) {
					proceedIsCustomerAlreadyRegistered((ClientQuery) clientQuery);
				}
			}
			if (clientQuery instanceof ClientQuery) {
				if (((ClientQuery) clientQuery).type.equals("fetchCustomer")) {
					fetchCustomer((ClientQuery) clientQuery);
				}
			}
			if (((ClientQuery) clientQuery).type
					.equals("changeCustomerFirstName")) {
				proceedChangeCustomerFirstName((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type
					.equals("changeCustomerLastName")) {
				proceedChangeCustomerLastName((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type
					.equals("changeCustomerPassword")) {
				proceedChangeCustomerPassword((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type.equals("addVehicle")) {
				proceedAddVehicle((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type.equals("fetchAllServices")) {
				proceedFetchAllServices((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type.equals("createOrder")) {
				proceedcreateOrder((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type.equals("fetchOrderID")) {
				proceedfetchOrderID((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type.equals("fetchVehicleID")) {
				proceedfetchVehicleID((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type.equals("createServiceOrders")) {
				proceedCreateServiceOrders((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type
					.equals("fetchOrdersByCustomerID")) {
				proceedFetchOrdersByCustomerID((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type
					.equals("fetchServiceOrdersByCustomerID")) {
				proceedFetchServiceOrdersByCustomerID((ClientQuery) clientQuery);
			}
			if (((ClientQuery) clientQuery).type
					.equals("changeCustomerAccountBalance")) {
				proceedChangeAccountBalance((ClientQuery) clientQuery);
			}
		}
	}

	private void proceedChangeAccountBalance(ClientQuery clientQuery) {
		String customerID = clientQuery.parameters[0];
		String accountBalance = clientQuery.parameters[1];

		System.out.println("ZMIENIAM WART@CSC");

		Server.complexDatabaseManager.customerAccountDatabaseManager
				.updateCustomerAccountBalance(customerID, accountBalance);
	}

	private void proceedFetchServiceOrdersByCustomerID(ClientQuery clientQuery) {
		String customerID = clientQuery.parameters[0];

		ArrayList<String[]> data = Server.complexDatabaseManager.orderDatabaseManager
				.fetchServiceOrdersByCustomerID(customerID);

		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(data);

		ClientQuery response = (new ClientQuery(
				"fetchServiceOrdersByCustomerID"));
		response.setAdditionalObjects(objects);
		System.out.println("Wyszukaem fetchServiceOrdersByCustomerID");
		sendResponce(response);
	}

	private void proceedFetchOrdersByCustomerID(ClientQuery clientQuery) {
		String customerID = clientQuery.parameters[0];

		ArrayList<String[]> data = Server.complexDatabaseManager.orderDatabaseManager
				.getOrdersByCustomerID(customerID);

		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(data);

		ClientQuery response = (new ClientQuery("fetchOrdersByCustomerID"));
		response.setAdditionalObjects(objects);
		sendResponce(response);
	}

	private void proceedCreateServiceOrders(ClientQuery clientQuery) {
		String serviceID = clientQuery.parameters[0];
		String orderID = clientQuery.parameters[1];
		System.out.println("DANE: " + serviceID + " " + orderID);
		Server.complexDatabaseManager.orderDatabaseManager.createServiceOrders(
				serviceID, orderID);
	}

	private void proceedfetchVehicleID(ClientQuery clientQuery) {
		String vehicleVIN = clientQuery.parameters[1];
		String vehicleID = Server.complexDatabaseManager.vehicleDatabaseManager
				.fetchVehicleIDByVIN(vehicleVIN);

		ClientQuery response = (new ClientQuery("fetchVehicleID"));
		response.parameters[0] = vehicleID;
		response.parameters[1] = vehicleVIN;
		sendResponce(response);
	}

	private void proceedfetchOrderID(ClientQuery clientQuery) {
		String vehicleID = clientQuery.parameters[0];

		String orderID = Server.complexDatabaseManager.orderDatabaseManager
				.getOrderByVehicleID(vehicleID);

		ClientQuery response = (new ClientQuery("fetchOrderID"));
		response.parameters[0] = orderID;
		sendResponce(response);
	}

	private void proceedcreateOrder(ClientQuery clientQuery) {
		String vehicleID = clientQuery.parameters[0];

		System.out.println("Odbieram: " + vehicleID);
		Server.complexDatabaseManager.orderDatabaseManager
				.createOrder(vehicleID);
	}

	private void proceedFetchAllServices(ClientQuery clientQuery) {
		ClientQuery response = (new ClientQuery("fetchAllServices"));

		ArrayList<String[]> servicesStrings = Server.complexDatabaseManager.serviceDatabaseManager
				.fetchAllServices();
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(servicesStrings);
		response.setAdditionalObjects(objects);
		System.out.println(objects.size());
		sendResponce(response);
	}

	private void proceedAddVehicle(ClientQuery clientQuery) {
		String vin = clientQuery.parameters[0];
		String mark = clientQuery.parameters[1];
		String model = clientQuery.parameters[2];
		String customerIDNumber = clientQuery.parameters[3];

		Server.complexDatabaseManager.vehicleDatabaseManager.createVehicle(vin,
				mark, model, customerIDNumber);
	}

	private void proceedChangeCustomerPassword(ClientQuery response) {
		String idNumber = response.parameters[0];
		String password = response.parameters[1];

		Server.complexDatabaseManager.customerAccountDatabaseManager
				.changeCustomerPassword(idNumber, password);
	}

	private void proceedChangeCustomerLastName(ClientQuery response) {
		String idNumber = response.parameters[0];
		String lastName = response.parameters[1];

		Server.complexDatabaseManager.customerAccountDatabaseManager
				.changeCustomerLastName(idNumber, lastName);
	}

	private void proceedChangeCustomerFirstName(ClientQuery response) {
		String idNumber = response.parameters[0];
		String firstName = response.parameters[1];

		Server.complexDatabaseManager.customerAccountDatabaseManager
				.changeCustomerFirstName(idNumber, firstName);
	}

	private void proceedIsUserInSystemDatabase(ClientQuery clientQuery) {
		ClientQuery response = (new ClientQuery("isUserInSystemDatabase"));

		boolean isEmployeeInSystemDatabase = Server.complexDatabaseManager.employeeDatabaseManager
				.isEmployeeInSystemDatabase(clientQuery.parameters[0],
						clientQuery.parameters[1]);
		if (isEmployeeInSystemDatabase) {
			response.isResponseTrue = true;

			sendResponce(response);
			return;
		} else {
			boolean isCustomerInSystemDatabase = Server.complexDatabaseManager.customerAccountDatabaseManager
					.isCustomerInSystemDatabase(clientQuery.parameters[0],
							clientQuery.parameters[1]);
			response.parameters[0] = clientQuery.parameters[0];
			response.isResponseTrue = isCustomerInSystemDatabase;

			sendResponce(response);
		}

	}

	private void proceedCreateCustomer(ClientQuery clientQuery) {
		ClientQuery response = (new ClientQuery("createCustomer"));
		String idNumber = clientQuery.parameters[0];
		String pesel = clientQuery.parameters[1];
		String password = clientQuery.parameters[2];
		boolean isResponseTrue = Server.complexDatabaseManager.customerAccountDatabaseManager
				.createCustomer(idNumber, pesel, password);

		response.isResponseTrue = isResponseTrue;

		sendResponce(response);
	}

	private void fetchCustomer(ClientQuery clientQuery) {
		ClientQuery response = (new ClientQuery("fetchCustomer"));
		String idNumber = clientQuery.parameters[0];
		String[] responseData = Server.complexDatabaseManager.customerAccountDatabaseManager
				.fetchCustomerFromDatabase(idNumber);

		response.parameters[0] = responseData[0];
		response.parameters[1] = responseData[1];
		response.parameters[2] = responseData[2];
		response.parameters[3] = responseData[3];
		response.parameters[4] = responseData[4];
		response.parameters[5] = responseData[5];
		response.parameters[6] = responseData[6];
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(Server.complexDatabaseManager.vehicleDatabaseManager
				.fetchVehiclesByCustomerID(responseData[0]));
		response.setAdditionalObjects(objects);

		sendResponce(response);
	}

	private void proceedIsCustomerAlreadyRegistered(ClientQuery clientQuery) {
		ClientQuery response = (new ClientQuery("isCustomerAlreadyRegistered"));
		String idNumber = clientQuery.parameters[0];
		boolean isResponseTrue = Server.complexDatabaseManager.customerAccountDatabaseManager
				.isCustomerAlreadyRegistered(idNumber);

		response.isResponseTrue = isResponseTrue;

		sendResponce(response);
	}

	private void sendResponce(ClientQuery response) {
		try {
			out.writeObject(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Object convertClientQuery() {
		Object clientQuery = null;
		try {
			clientQuery = in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientQuery;
	}
}
