package Business.Order.Payment;

public interface PaymentExecution {

	public boolean executePayment();

	public boolean canExecutePayent();

	public void accountBalanceUpdate();

	public void vehicleOrdersHistoryUpdate();

	public void customerDiscountsUpdate();

}
