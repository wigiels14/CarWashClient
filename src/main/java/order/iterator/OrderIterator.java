package order.iterator;

import iterator.CustomIterator;
import order.Order;

public interface OrderIterator extends CustomIterator {

	@Override
	public Order next();

	@Override
	public Order prvious();

	@Override
	public boolean hasNext();

}
