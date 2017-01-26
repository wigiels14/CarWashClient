package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Business.Vehicle.Vehicle;
import Server.Server;

public class VehicleDatabaseManager {
	public Vehicle[] fetchVehiclesByCustomerID(String customerID) {
		Vehicle[] vehicles = new Vehicle[20];
		String query = "SELECT ID, MARK, MODEL FROM VEHICLE WHERE CUSTOMER_ID = ?";

		PreparedStatement myStatement;
		ResultSet queryResult = null;
		try {
			myStatement = Server.complexDatabaseManager.CONNECTION
					.prepareStatement(query);
			myStatement.setInt(1, Integer.decode(customerID));

			queryResult = myStatement.executeQuery();

			String id = null, mark = null, carModel = null;
			int iterator = 0;
			while (queryResult.next()) {
				vehicles[iterator] = new Vehicle();
				vehicles[iterator].setId(queryResult.getString("ID"));
				vehicles[iterator].setBrand(queryResult.getString("BRAND"));
				vehicles[iterator].setCarModel(queryResult.getString("MODEL"));
			}
			return vehicles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
