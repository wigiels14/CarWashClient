package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import Database.ComplexDatabaseManager;

public class Server {
	public static int PORT = 8080;
	public static ServerSocket serverSocket;
	public static int userNumber = 0;
	public static Connection connection;
	public static ConnectedClient[] connectedClient = new ConnectedClient[100];
	public static ComplexDatabaseManager complexDatabaseManager;

	public static void main(String[] args) throws IOException, SQLException {
		complexDatabaseManager = ComplexDatabaseManager.getInstance();
		System.out.println(complexDatabaseManager);
		connection = complexDatabaseManager.CONNECTION;

		serverSocket = new ServerSocket(PORT);

		while (true) {
			Socket socket = serverSocket.accept();
			if (socket.isConnected()) {
				System.out.println("CONNECTUJE USERA");
				connectedClient[userNumber] = new ConnectedClient(connection,
						serverSocket, socket);
				connectedClient[userNumber].start();
				userNumber++;
			}
		}

	}
}