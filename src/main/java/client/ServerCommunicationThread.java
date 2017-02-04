package client;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import order.Order;
import person.Customer;
import service.Service;
import service.ServiceFactory;
import vehicle.Vehicle;

public class ServerCommunicationThread extends Thread {

	@Override
	public void run() {
		while (true) {
			Object response = convertServerResponce();

			if (response instanceof ClientQuery) {
				if (((ClientQuery) response).type.equals("isUserInSystemDatabase")) {
					proceedIsUserInSystemDatabaseResponce((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("createCustomer")) {
					proceedCreateCustomer((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("isCustomerAlreadyRegistered")) {
					proceedIsCustomerAlreadyRegistered((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchCustomer")) {
					proceedFetchCustomer((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchEmployee")) {
					proceedFetchEmployee((ClientQuery) response);
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
				if (((ClientQuery) response).type.equals("fetchOrdersByCustomerID")) {
					proceedFetchOrdersByCustomerID((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchServiceOrdersByCustomerID")) {
					proceedFetchServiceOrdersByCustomerID((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("addEmployeeOrder")) {
					proceedAddEmployeeOrder((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchEmployeeOrders")) {
					proceedEmployeeOrders((ClientQuery) response);
				}
				if (((ClientQuery) response).type.equals("fetchNotTakenOrders")) {
					proceedFetchNotTakenOrders((ClientQuery) response);
				}
			}
		}
	}

	private void proceedAddEmployeeOrder(ClientQuery response) {
		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.showAllOrdersPanel.sendFetchNotTakenOrders());
	}

	private void proceedEmployeeOrders(ClientQuery response) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Object object : response.getAdditionalObjects()) {
			String[] convertedObject = (String[]) object;

			Vehicle orderVehicle = new Vehicle();
			orderVehicle.setId(convertedObject[3]);
			orderVehicle.setVin(convertedObject[4]);
			orderVehicle.setMark(convertedObject[5]);
			orderVehicle.setCarModel(convertedObject[6]);

			String orderID = convertedObject[0];
			String orderState = convertedObject[2];

			Service covertedService = null;

			switch (convertedObject[7]) {
			case ("TunelWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.TUNELWASHSTASIONSERVICE);
				break;
			case ("TouchlessWashStationService"):
				covertedService = ServiceFactory
						.getInstance(ServiceFactory.WashStationType.TOUCHLESSWASHSTATIONSERVICE);
				break;
			case ("SteamWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.STEAMWASHSTATIONSERVICE);
				break;
			case ("ManualWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.MANUALWASHSTATIONSERVICE);
				break;
			}

			covertedService.setName(convertedObject[8]);

			Order order = new Order.OrderBuilder().createState(convertedObject[2]).createVehicle(orderVehicle)
					.createID(orderID).createService(covertedService).build();
			orders.add(order);
		}
		Client.mainEmployeeInterfacePanel.yourOrdersPanel.setOrders(orders);
		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.setCenterYourOrdersPanel());
	}

	private void proceedFetchNotTakenOrders(ClientQuery response) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Object object : response.getAdditionalObjects()) {
			String[] convertedObject = (String[]) object;

			Vehicle orderVehicle = new Vehicle();
			orderVehicle.setId(convertedObject[3]);
			orderVehicle.setVin(convertedObject[4]);
			orderVehicle.setMark(convertedObject[5]);
			orderVehicle.setCarModel(convertedObject[6]);

			String orderID = convertedObject[0];
			String orderState = convertedObject[2];

			Service covertedService = null;

			switch (convertedObject[7]) {
			case ("TunelWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.TUNELWASHSTASIONSERVICE);
				break;
			case ("TouchlessWashStationService"):
				covertedService = ServiceFactory
						.getInstance(ServiceFactory.WashStationType.TOUCHLESSWASHSTATIONSERVICE);
				break;
			case ("SteamWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.STEAMWASHSTATIONSERVICE);
				break;
			case ("ManualWashStationService"):
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.MANUALWASHSTATIONSERVICE);
				break;
			}

			covertedService.setName(convertedObject[8]);

			Order order = new Order.OrderBuilder().createState(convertedObject[2]).createVehicle(orderVehicle)
					.createID(orderID).createService(covertedService).build();
			orders.add(order);
		}
		Client.mainEmployeeInterfacePanel.showAllOrdersPanel.setOrders(orders);
		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.setCenterShowAllOrdersPanel());
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
			for (Vehicle vehicle : Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer()
					.getVehicleFleet()) {
				if (vehicle.getId().equals(convertedObject[1]))
					orderVehicle = vehicle;
			}
			String orderState = convertedObject[2];

			Order order = new Order.OrderBuilder().createState(convertedObject[2])
					.createCustomer(Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer())
					.createVehicle(orderVehicle).createID(orderID).build();
			orders.add(order);
		}
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer().setOrders(orders);

		Client.mainCustomerInterfacePanel.showActiveOrdersPanel.sendFetchServiceOrdersByCustomerID(
				Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomerID());
	}

	private void proceedfetchVehiceID(ClientQuery response) {
		Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel.getCustomer();

		String vehicleVIN = response.parameters[1];
		String vehicleID = response.parameters[0];

		ArrayList<Vehicle> vehicles = customer.getVehicleFleet();

		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVin().equals(vehicleVIN)) {
				vehicle.setId(vehicleID);
			}
		}
		Client.mainCustomerInterfacePanel.addOrderPanel.sendFetchOrderID(vehicleID);
	}

	private void proceedfetchOrderID(ClientQuery response) {
		Client.mainCustomerInterfacePanel.addOrderPanel.getCarWashCardPaymentExecution().getOrder()
				.setId(response.parameters[0]);
		Client.mainCustomerInterfacePanel.addOrderPanel.sendCreateServiceOrders();
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
				covertedService = ServiceFactory.getInstance(ServiceFactory.WashStationType.STEAMWASHSTATIONSERVICE);
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

	private void proceedFetchEmployee(ClientQuery response) {
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setId(response.parameters[0]);
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setPassword(response.parameters[1]);
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setType(response.parameters[2]);
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setFirstName(response.parameters[3]);
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setLastName(response.parameters[4]);
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setPesel(response.parameters[5]);
		Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.getEmployee().setIdNumber(response.parameters[5]);

		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.setIDText());
		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.setFirstNameText());
		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.setLastNameText());
		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.topEmployeeInterfacePanel.setTypeText());

		Platform.runLater(() -> Client.mainEmployeeInterfacePanel.setTopEmployeeInterfacePanel());
	}

	private void setEmployeeInterfaceSceneActive() {
		Platform.runLater(() -> Client.setEmployeeInterfaceSceneActive());
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

	private void fetchEmployeeFromDatabase(String idNumber) {
		try {
			ClientQuery clientQuery = new ClientQuery("fetchEmployee");
			clientQuery.parameters[0] = idNumber;
			Client.out.writeObject(clientQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void proceedIsUserInSystemDatabaseResponce(ClientQuery response) {
		if (response.isResponseTrue) {
			String whoIsLoggedIn = response.parameters[9];
			if (whoIsLoggedIn.equals("customer")) {
				fetchCustomerFromDatabase(response.parameters[0]);
				setCustmerInterfaceSceneActive();
			} else {
				fetchEmployeeFromDatabase(response.parameters[0]);
				setEmployeeInterfaceSceneActive();
			}
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
