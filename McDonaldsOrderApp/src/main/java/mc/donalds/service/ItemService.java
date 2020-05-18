package mc.donalds.service;

import java.util.List;

import mc.donalds.entity.Item;

public interface ItemService {
	Item getItem(int id);
	List<Item> getAllItems();
	void saveItem(Item item);
	void updateItem(Item item);
	void deleteItem(Item item);
}
