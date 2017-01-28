package Business.Order.Payment;

import Business.Order.Order;
import Business.Person.Customer;
import Starting.Client;

public class CarWashCardPaymentExecution implements PaymentExecution {
	private Payment payment;
	private Order order;
	private Customer customer;

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

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
		double serviceCost = order.getCost();
		double accountBalace = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomerAccountBalance();
		if (canExecutePayment(accountBalace, serviceCost)) {
			double newAccountBalace = payment.executePayment(
					customer.getAccountBalance(), order.getCost());

			System.out.println(newAccountBalace);
			accountBalanceUpdate(newAccountBalace);
			return true;
		}
		return false;
	}

	@Override
	public boolean canExecutePayment(double accountBalace, double serviceCost) {
		if (accountBalace > serviceCost)
			return true;
		else
			return false;
	}

	@Override
	public void accountBalanceUpdate(double newAccountBalance) {
		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.setCustomerAccountBalance(String.valueOf(newAccountBalance));

		Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.accountBalaceText();
		Customer customer = Client.mainCustomerInterfacePanel.topCustomerInterfacePanel
				.getCustomer();
		Client.mainCustomerInterfacePanel.changePersonalDataPanel
				.sendChangeCustomerAccountBalance(customer.getId(),
						String.valueOf(newAccountBalance));
	}

	@Override
	public void vehicleOrdersHistoryUpdate() {

	}

	@Override
	public void customerDiscountsUpdate() {
	}

}
