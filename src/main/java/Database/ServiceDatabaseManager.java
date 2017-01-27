package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Server.Server;

public class ServiceDatabaseManager {

	public ArrayList<String[]> fetchServicesByType(String serviceType) {
		ArrayList<String[]> services = new ArrayList<String[]>();
		String query = "SELECT ID, SERVICE_TYPE, NAME, COST FROM SERVICE WHERE SERVICE_TYPE = ?";

		PreparedStatement myStatement;
		ResultSet queryResult = null;
		try {
			myStatement = Server.complexDatabaseManager.CONNECTION
					.prepareStatement(query);
			myStatement.setString(1, serviceType);

			queryResult = myStatement.executeQuery();

			String id = null, name = null, cost = null;
			while (queryResult.next()) {
				id = queryResult.getString("ID");
				serviceType = queryResult.getString("SERVICE_TYPE");
				name = queryResult.getString("NAME");
				cost = queryResult.getString("COST");
				services.add(new String[] { id, serviceType, name, cost });
			}
			return services;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String[]> fetchAllServices() {
		ArrayList<String[]> services = new ArrayList<String[]>();

		ArrayList<String[]> tunelWashStationService = fetchServicesByType("TunelWashStationService");
		ArrayList<String[]> touchlessWashStationService = fetchServicesByType("TouchlessWashStationService");
		ArrayList<String[]> steamWashStationService = fetchServicesByType("SteamWashStationService");
		ArrayList<String[]> manualWashStationService = fetchServicesByType("ManualWashStationService");

		services.addAll(tunelWashStationService);
		services.addAll(touchlessWashStationService);
		services.addAll(steamWashStationService);
		services.addAll(manualWashStationService);

		return services;
	}

}
