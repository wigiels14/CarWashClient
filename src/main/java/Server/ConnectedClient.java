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
					proceedisCustomerAlreadyRegistered((ClientQuery) clientQuery);
				}
			}
		}
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

	private void proceedisCustomerAlreadyRegistered(ClientQuery clientQuery) {
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
