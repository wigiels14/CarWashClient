package Business.Order.Payment;

import Business.Order.Order;

public class CarWashCardPaymentExecution implements PaymentExecution {
	private Payment payment;
	private Order order;

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
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
			return true;
		else
			return false;
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
