package Business.Order;

import java.util.ArrayList;

import Business.Iterator.CustomIterator;
import Business.Iterator.CustomRepository;
import Business.Order.Iterator.FinishedOrderIterator;
import Business.Order.Iterator.NotFinishedOrderIterator;
import Business.Order.Iterator.OrderIteratorType;

public class OrderRepository implements CustomRepository {
	private final ArrayList<Order> orders;

	public OrderRepository() {
		orders = new ArrayList<>();
	}

	@Override
	public CustomIterator getIterator(OrderIteratorType orderType,
			ArrayList<Order> orders) {
		System.out.println("orders size " + orders.size());
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
