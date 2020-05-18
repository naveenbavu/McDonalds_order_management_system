package mc.donalds.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mc.donalds.entity.Bill;
import mc.donalds.exceptions.DatabaseAccessException;
import mc.donalds.logic.Order;
import mc.donalds.service.BillService;
import mc.donalds.service.OrderService;

@Component
@Scope("prototype")
public class EmployeeFacade extends UniFacade {

	@Autowired
	private BillService billService;
	
	@Autowired
	private OrderService orderService;
	
	public Bill getBill(int billId) {
		return billService.getBill(billId);
	}
	
	//@Transactional
	public void changeBillToPayed(int billId) throws DatabaseAccessException {
		billService.updateBillPayed(billId);
	}
	
	public Order getOrder(int orderId) {
		return orderService.getOrder(orderId);
	}
}
