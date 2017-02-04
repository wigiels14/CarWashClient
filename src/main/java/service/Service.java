package service;

import java.util.LinkedHashSet;
import java.util.Set;

import order.payment.Payment;

public abstract class Service implements Cloneable {
	private String id;
	protected String name;
	protected double cost;
	private final Set<Payment> payments = new LinkedHashSet<Payment>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPayment(Payment payment) {
		this.payments.add(payment);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	@Override
	public Object clone() {
		Object clone = null;

		try {
			clone = super.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return clone;
	}
}
