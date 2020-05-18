package mc.donalds.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mc.donalds.entity.Item;
import mc.donalds.entity.ItemType;
import mc.donalds.service.ItemService;
import mc.donalds.service.ItemTypeService;

public class UniFacade {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	public List<Item> getItems(){
		return itemService.getAllItems();
	}
	
	public List<ItemType>getTypes(){
		return itemTypeService.getAllTypes();
	}
}
