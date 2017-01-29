package Business.Iterator;

import java.util.ArrayList;

import Business.Order.Order;
import Business.Order.Iterator.OrderIteratorType;

public interface CustomRepository {

	public CustomIterator getIterator(OrderIteratorType orderType, ArrayList<Order> orders);

}
