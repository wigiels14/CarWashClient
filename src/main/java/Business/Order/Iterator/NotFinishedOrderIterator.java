package Business.Order.Iterator;

import java.util.ArrayList;

import Business.Order.Order;

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
		System.out.println(index + "    " + orders.size());
		while (index < orders.size()) {
			Order c = orders.get(index);
			System.out.println(c.getState());
			if (c.getState().equals("active")
					|| c.getState().equals("in process")) {
				System.out.println("wiad: " + index);
				return true;
			} else
				index++;
		}
		return false;
	}

}