package mc.donalds.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mc.donalds.entity.ItemType;
import mc.donalds.exceptions.ItemException;
import mc.donalds.logic.Order;
import mc.donalds.service.ItemTypeService;
import mc.donalds.service.OrderService;

@Component
@Scope("prototype")
public class CustomerFacade extends UniFacade {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	@Autowired
	private AnnotationConfigApplicationContext context;
	
	public Order getOrder() {
		return context.getBean(Order.class);
	}
	
	public void addNewItemType(ItemType itemType){
		itemTypeService.saveType(itemType);
	}
	
	//@Transactional
	public int processOrder(Order order) throws ItemException {
		return orderService.saveOrder(order);
	}
	
	public String peekOrder(int orderId) {
		Order order = orderService.getOrder(orderId);
		StringBuilder builder = new StringBuilder();
		builder.append(order.getBillId() + "\n");
		order.getOrderList().stream().forEach(item -> builder.append(item.getName() + "    -    " + item.getPrice() + "\n"));
		builder.append("Total amount is :      " + order.getTotalCost());
		
		return builder.toString();
	}
}
