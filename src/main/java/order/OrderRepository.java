package order;

import iterator.CustomIterator;
import iterator.CustomRepository;

import java.util.ArrayList;

import order.iterator.FinishedOrderIterator;
import order.iterator.NotFinishedOrderIterator;
import order.iterator.OrderIteratorType;

public class OrderRepository implements CustomRepository {
	private final ArrayList<Order> orders;

	public OrderRepository() {
		orders = new ArrayList<>();
	}

	@Override
	public CustomIterator getIterator(OrderIteratorType orderType,
			ArrayList<Order> orders) {
		if (orderType.equals(OrderIteratorType.FINISHED)) {
			return new FinishedOrderIterator(orders);
		}
		if (orderType.equals(OrderIteratorType.NOT_FINISHED)) {
			return new NotFinishedOrderIterator(orders);
		}
		return null;
	}

	@Override
	public void add(Object o) {
		Order order = (Order) o;
		this.orders.add(order);
	}

	@Override
	public void remove(Object o) {
		Order order = (Order) o;
		this.orders.remove(o);
	}

}
