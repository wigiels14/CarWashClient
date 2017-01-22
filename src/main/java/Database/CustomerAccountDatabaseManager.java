package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Server.Server;

public class CustomerAccountDatabaseManager {

	private final CustomerAccountManagerProxy customerAccountManagerProxy;

	public CustomerAccountDatabaseManager() {
		customerAccountManagerProxy = new CustomerAccountManagerProxy();

	}

	class CustomerAccountManagerProxy {

		boolean isCustomerInSystemDatabase(String idNumber, String password) {
			String query = "SELECT COUNT(*) AS amount FROM CUSTOMER WHERE ID_NUMBER = ? AND CUSTOMER_PASSWORD =  ?";

			PreparedStatement myStatement;
			ResultSet queryResult = null;
			try {
				myStatement = Server.complexDatabaseManager.CONNECTION
						.prepareStatement(query);
				myStatement.setString(1, idNumber);
				myStatement.setString(2, password);

				queryResult = myStatement.executeQuery();

				while (queryResult.next()) {
					String resultString = queryResult.getString("amount");
					if (resultString.equals("1")) {
						return true;
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		boolean isCustomerAlreadyRegistered(String idNumber) {
			String query = "SELECT COUNT(*) AS amount FROM CUSTOMER WHERE ID_NUMBER = ?";

			PreparedStatement myStatement;
			ResultSet queryResult = null;
			try {
				myStatement = Server.complexDatabaseManager.CONNECTION
						.prepareStatement(query);
				myStatement.setString(1, idNumber);

				queryResult = myStatement.executeQuery();

				while (queryResult.next()) {
					String resultString = queryResult.getString("amount");
					if (resultString.equals("1")) {
						return true;
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		boolean createCustomer(String idNumber, String pesel, String password) {
			String query = "SELECT create_customer(?,?,?);";

			PreparedStatement myStatement;
			ResultSet queryResult = null;
			try {
				myStatement = Server.complexDatabaseManager.CONNECTION
						.prepareStatement(query);
				myStatement.setString(1, idNumber);
				myStatement.setString(2, pesel);
				myStatement.setString(3, password);

				queryResult = myStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}

	public boolean isCustomerInSystemDatabase(String idNumber, String password) {
		return customerAccountManagerProxy.isCustomerInSystemDatabase(idNumber,
				password);
	}

	public boolean isCustomerAlreadyRegistered(String idNumber) {
		return customerAccountManagerProxy
				.isCustomerAlreadyRegistered(idNumber);
	}

	public boolean createCustomer(String idNumber, String pesel, String password) {
		return customerAccountManagerProxy.createCustomer(idNumber, pesel,
				password);
	}
}
