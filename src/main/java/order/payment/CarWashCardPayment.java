package order.payment;

public class CarWashCardPayment implements Payment {

	@Override
	public double executePayment(double accountBalace, double serviceCost) {
		double newAccountBalance = accountBalace - serviceCost;
		return newAccountBalance;
	}

	@Override
	public String toString() {
		return "Car wash card payment";
	}
}
