package Business.Order.Payment;

public interface PaymentExecution {

	public boolean executePayment();

	public boolean canExecutePayment(double accountBalace, double serviceCost);

	public void vehicleOrdersHistoryUpdate();

	public void customerDiscountsUpdate();

	void accountBalanceUpdate(double newAccountBalance);

}
