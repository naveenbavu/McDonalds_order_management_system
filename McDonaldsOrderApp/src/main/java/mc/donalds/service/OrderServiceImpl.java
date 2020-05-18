package mc.donalds.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.donalds.entity.Bill;
import mc.donalds.entity.Item;
import mc.donalds.exceptions.ItemException;
import mc.donalds.logic.Order;
import mc.donalds.logic.ReadJSONOrder;
import mc.donalds.logic.WriteJSONOrder;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private BillService billService;
	@Autowired
	private WriteJSONOrder writeJSONOrder;
	@Autowired
	private ReadJSONOrder readJSONOrder;
	@Autowired
	private ItemService itemService;
	
	public Order getOrder(int orderId) {
		Order order = null;
		if(orderId > 0)
			order = readJSONOrder.getOrder(orderId);
		return order;
	}

	public int saveOrder(Order order) throws ItemException{
		if(order != null) {
			itemsCheck(order.getOrderList());
			Bill bill = new Bill();
			DecimalFormat format = new DecimalFormat("#.00");
			System.out.println("------------------------------------------------------------");
			System.out.println(order.getTotalCost());
			System.out.println("------------------------------------------------------------");
			bill.setTotalAmount(Double.parseDouble(format.format(order.getTotalCost())));
			bill.setPayed(false);
			int billId = billService.saveBill(bill);
			order.setBillId(billId);
			boolean saved = writeJSONOrder.saveOrder(order);
			if(saved)
				return billId;
		}
		return -1;
	}
	
	private void itemsCheck(List<Item> items) throws ItemException {
		for(Item item : items) {
			if(itemService.getItem(item.getId()) == null)
				throw new ItemException("Item not fount. Item : " + item);
		}
	}
}

