package mc.donalds.service;

import mc.donalds.exceptions.ItemException;
import mc.donalds.logic.Order;

public interface OrderService {
	Order getOrder(int orderId);
	int saveOrder(Order order) throws ItemException;
	
}
