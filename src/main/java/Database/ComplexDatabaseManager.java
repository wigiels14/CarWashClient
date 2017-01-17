package Database;

public class ComplexDatabaseManager {
	public static ComplexDatabaseManager databaseManagerInstance;
	public CustomerAccountDatabaseManager customerAccountDatabaseManager;
	public VehicleDatabaseManager vehicleDatabaseManager;
	public EmployeeDatabaseManager employeeDatabaseManager;
	public ServiceDatabaseManager serviceDatabaseManager;
	public PaymentDatabaseManager paymentDatabaseManager;

	private ComplexDatabaseManager() {
		customerAccountDatabaseManager = new CustomerAccountDatabaseManager();
		vehicleDatabaseManager = new VehicleDatabaseManager();
		employeeDatabaseManager = new EmployeeDatabaseManager();
		serviceDatabaseManager = new ServiceDatabaseManager();
		paymentDatabaseManager = new PaymentDatabaseManager();

	}

}
