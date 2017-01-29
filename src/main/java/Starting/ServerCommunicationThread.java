package Starting;

import java.io.IOException;
import java.util.ArrayList;

import Business.Order.Order;
import Business.Order.OrderBuilder;
import Business.Person.Customer;
import Business.Service.*;
import Business.Vehicle.Vehicle;
import Database.ServiceDatabaseManager;
import Server.ClientQuery;
import Server.Server;
import javafx.application.Platform;

public class ServerCommunicationThread extends Thread {

	@Override
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
				if (((ClientQuery) response).type.equals("fetchAllServices")) {
					proceedFetchAllServices((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchOrderID")) {
					proceedfetchOrderID((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchVehicleID")) {
					proceedfetchVehiceID((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("createServiceOrders")) {
					proceedFetchOrdersByCustomerID((ClientQuery) response);
				}
				if (((ClientQuery) response).type
						.equals("fetchOrdersByCustomerID")) {
					proceedFetchOrdersByCustomerID((ClientQuery) response);
				}
				if (((ClientQuery) response).type
						.equals("fetchServiceOrdersByCustomerID")) {
					proceedFetchServiceOrdersByCustomerID((ClientQuery) response);
				}
			}
		}
	}

	private void proceedFetchServiceOrdersByCustomerID(ClientQuery response) {
		ArrayList<Order> finalOrders = new ArrayList<Order>();
		ArrayList<Order> orders = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().getOrders();

		ArrayList<Service> services = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getServices();

		for (Object object : response.getAdditionalObjects()) {
			String[] convertedObject = (String[]) object;
			String serviceID = convertedObject[0];
			String orderID = convertedObject[1];
			for (Order order : orders) {
				if (order.getId().equals(orderID)) {
					for (Service service : services) {
						if (service.getId().equals(serviceID)) {
							order.setService(service);
							break;
						}
					}
					finalOrders.add(order);
				}
			}
		}
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().setOrders(finalOrders);
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.setCenterShowActiveOrdersInterfacePanel());
	}

	private void proceedFetchOrdersByCustomerID(ClientQuery response) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Object object : response.getAdditionalObjects()) {
			String[] convertedObject = (String[]) object;

			String orderID = convertedObject[0];
			Vehicle orderVehicle = null;
			for (Vehicle vehicle : Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
					.getCustomer().getVehicleFleet()) {
				if (vehicle.getId().equals(convertedObject[1]))
					orderVehicle = vehicle;
			}
			String orderState = convertedObject[2];

			Order order = new Order.OrderBuilder()
					.createState(convertedObject[2])
					.createCustomer(
							Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
									.getCustomer()).createVehicle(orderVehicle)
					.createID(orderID).build();
			System.out.println("SD" + order.getState());
			orders.add(order);
		}
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer().setOrders(orders);

		Client.mainCustomerInterfacePanel.showActiveOrdersPanel
				.sendFetchServiceOrdersByCustomerID(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
						.getCustomerID());
	}

	private void proceedfetchVehiceID(ClientQuery response) {
		Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer();

		String vehicleVIN = response.parameters[1];
		String vehicleID = response.parameters[0];

		ArrayList<Vehicle> vehicles = customer.getVehicleFleet();

		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVin().equals(vehicleVIN)) {
				vehicle.setId(vehicleID);
			}
		}
		Client.mainCustomerInterfacePanel.addOrderPanel
				.sendFetchOrderID(vehicleID);
	}

	private void proceedfetchOrderID(ClientQuery response) {
		System.out.println("Dostaled ID Orderu: " + response.parameters[0]);
		Client.mainCustomerInterfacePanel.addOrderPanel
				.getCarWashCardPaymentExecution().getOrder()
				.setId(response.parameters[0]);
		Client.mainCustomerInterfacePanel.addOrderPanel
				.sendCreateServiceOrders();
	}

	private void proceedFetchAllServices(ClientQuery response) {
		for (Object object : response.getAdditionalObjects()) {
			String[] convertedObject = (String[]) object;
			Service covertedService = null;
			switch (convertedObject[1]) {
			case ("TunelWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.TUNELWASHSTASIONSERVICE);
				break;
			case ("TouchlessWashStationService"):
				covertedService = ServiceFactory
						.getInstance(ServiceFactory.WashStationType.TOUCHLESSWASHSTATIONSERVICE);
				break;
			case ("SteamWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.TUNELWASHSTASIONSERVICE);
				break;
			case ("ManualWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.MANUALWASHSTATIONSERVICE);
				break;
			}
			covertedService.setId(convertedObject[0]);
			covertedService.setName(convertedObject[2]);
			covertedService.setCost(Double.parseDouble(convertedObject[3]));
			ArrayList<Service> services = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getServices();
			services.add(covertedService);
		}
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.addOrderPanel.setServicesParts());
	}

	private void proceedFetchCustomer(ClientQuery response) {
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerID(response.parameters[0]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerPassword(response.parameters[1]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerFirstName(response.parameters[2]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerLastName(response.parameters[3]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerPesel(response.parameters[4]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerIdNumber(response.parameters[5]);
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setCustomerAccountBalance(response.parameters[6]);
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setIDText());
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setFirstNameText());
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.setLastNameText());
		Platform.runLater(() -> Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.accountBalaceText());

		for (Object object : response.getAdditionalObjects()) {
			String[] convertedObject = (String[]) object;
			Vehicle covertedVehicle = new Vehicle();
			covertedVehicle.setId(convertedObject[0]);
			covertedVehicle.setVin(convertedObject[1]);
			covertedVehicle.setBrand(convertedObject[2]);
			covertedVehicle.setCarModel(convertedObject[3]);
			Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer();
			customer.addVehicle(covertedVehicle);
		}

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
