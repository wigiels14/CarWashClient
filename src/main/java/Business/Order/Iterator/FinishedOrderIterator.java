package Business.Order.Iterator;

import java.util.ArrayList;

import Business.Order.Order;

public class FinishedOrderIterator implements OrderIterator {
	private int index;
	private ArrayList<Order> orders = new ArrayList<Order>();

	public FinishedOrderIterator(ArrayList<Order> orderList) {
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
			if (c.getState().equals("finished")) {
				return true;
			} else
				index++;
		}
		return false;
	}

}
