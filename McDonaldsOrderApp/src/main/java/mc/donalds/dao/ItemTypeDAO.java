package mc.donalds.dao;

import java.util.List;

import mc.donalds.entity.ItemType;

public interface ItemTypeDAO {
	List<ItemType> getAllTypes();
	ItemType getItemType(int id);
	void saveItemType(ItemType itemType);
	void updateItemType(ItemType itemType);
	void deleteItemType(ItemType itemType);
	ItemType getItemType(String typeName);
}
