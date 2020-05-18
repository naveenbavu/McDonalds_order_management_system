package mc.donalds.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mc.donalds.entity.Bill;
import mc.donalds.entity.Item;
import mc.donalds.entity.ItemType;
import mc.donalds.exceptions.DatabaseAccessException;
import mc.donalds.logic.Order;
import mc.donalds.service.BillService;
import mc.donalds.service.ItemService;
import mc.donalds.service.ItemTypeService;
import mc.donalds.service.OrderService;

@Component
@Scope("prototype")
public class AdminFacade extends UniFacade {
	/*
	 * Admin moze sve sto moze i zaposleni Admin moze da gleda statistiku Admin moze
	 * da ubacuje nove proizvode Admin moze da ubacuje nove tipove Admin moze da
	 * gleda racune Admin moze da gleda order-e
	 * 
	 */
	@Autowired
	private OrderService orderService;

	@Autowired
	private BillService billService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemTypeService itemTypeService;
	
	public Order getOrder(int orderId) {
		return orderService.getOrder(orderId);
	}

	public Bill getBill(int billId) {
		return billService.getBill(billId);
	}
	
	public List<Bill> getBillsFrom(int dayOfMonth, int month, int year) {
		return billService.getBillsFrom(LocalDate.of(year, month, dayOfMonth));
	}

	public Item getItem(int itemId) {
		return itemService.getItem(itemId);
	}

	//@Transactional
	public void addNewItem(Item item){
		itemService.saveItem(item);
	}

	//@Transactional
	public void removeItem(Item item){
		itemService.deleteItem(item);
	}

	//@Transactional
	public void updateItem(Item item){
		itemService.updateItem(item);
	}

	public ItemType getItemType(int itemTypeId) {
		return itemTypeService.getType(itemTypeId);
	}

	//@Transactional
	public void addNewItemType(ItemType itemType){
		itemTypeService.saveType(itemType);
	}

	//@Transactional
	public void removeItemType(ItemType itemType) throws DatabaseAccessException {
		itemTypeService.deleteType(itemType);
	}

	//@Transactional
	public void updateItemType(ItemType itemType) throws DatabaseAccessException {
		itemTypeService.updateType(itemType);
	}

}
