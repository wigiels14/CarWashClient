package Business.Order.Payment;

import Business.Order.Order;

public class CarWashCardPaymentExecution implements PaymentExecution {
	private Order order;
	private Remmitance remmitance;

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setRemmitance(Remmitance remmitance) {
		this.remmitance = remmitance;
	}

	public Order getOrder() {
		return order;
	}

	public Remmitance getRemmitance() {
		return remmitance;
	}

	@Override
	public boolean executePayment() {
		if (canExecutePayent()) {

			return true;
		}
		return false;
	}

	@Override
	public boolean canExecutePayent() {
		if (order.getCustomer().getAccountBalance() > order.getCost())
			return false;
		else
			return true;
	}

	@Override
	public void accountBalanceUpdate() {
		double newAccountBalance = order.getCustomer().getAccountBalance()
				- order.getCost();
		order.getCustomer().setAccountBalance(newAccountBalance);

	}

	@Override
	public void vehicleOrdersHistoryUpdate() {

	}

	@Override
	public void customerDiscountsUpdate() {
	}

}
