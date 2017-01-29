package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Server.Server;

public class EmployeeDatabaseManager implements DatabaseManager {
	private final EmployeeDatabaseManagerProxy employeeDatabaseManagerProxy;

	public EmployeeDatabaseManager() {
		employeeDatabaseManagerProxy = new EmployeeDatabaseManagerProxy();

	}

	class EmployeeDatabaseManagerProxy {

		boolean isEmployeeInSystemDatabase(String idNumber, String password) {
			String query = "SELECT COUNT(*) AS amount FROM CAR_STATION_EMPLOYEE WHERE ID_NUMBER = ? AND CAR_STATION_EMPLOYEE_PASSWORD =  ?";

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
	}

	public boolean isEmployeeInSystemDatabase(String idNumber, String password) {
		return employeeDatabaseManagerProxy.isEmployeeInSystemDatabase(
				idNumber, password);
	}

}
