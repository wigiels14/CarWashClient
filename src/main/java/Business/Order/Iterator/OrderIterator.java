package Business.Order.Iterator;

import Business.Iterator.CustomIterator;
import Business.Order.Order;

public interface OrderIterator extends CustomIterator {

	@Override
	public Order next();

	@Override
	public Order prvious();

	@Override
	public boolean hasNext();

}
