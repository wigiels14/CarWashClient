package iterator;

import java.util.ArrayList;

import order.Order;
import order.iterator.OrderIteratorType;

public interface CustomRepository {

	public CustomIterator getIterator(OrderIteratorType orderType,
			ArrayList<Order> orders);

	public void add(Object o);

	public void remove(Object o);

}
