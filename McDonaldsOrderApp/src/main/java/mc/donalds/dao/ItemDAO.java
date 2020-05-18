package mc.donalds.dao;

import java.util.List;

import mc.donalds.entity.Item;

public interface ItemDAO {
	List<Item> getAllItems();
	Item getItem(int id);
	Item getItem(String name);
	void saveItem(Item item);
	void updateItem(Item item);
	void deleteItem(Item item);
}
