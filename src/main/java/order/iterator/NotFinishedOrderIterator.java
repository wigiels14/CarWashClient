package order.iterator;

import java.util.ArrayList;

import order.Order;

public class NotFinishedOrderIterator implements OrderIterator {
	private int index;
	private ArrayList<Order> orders = new ArrayList<Order>();

	public NotFinishedOrderIterator(ArrayList<Order> orderList) {
		orders = orderList;
	}

	@Override
	public Order next() {
		Order c = orders.get(index);
		index++;
		return c;
	}

	@Override
	public Order prvious() {
		Order c = orders.get(index);
		index--;
		return c;
	}

	@Override
	public boolean hasNext() {
		while (index < orders.size()) {
			Order c = orders.get(index);
			if (c.getState().equals("active")
					|| c.getState().equals("in process")) {
				return true;
			} else
				index++;
		}
		return false;
	}

}