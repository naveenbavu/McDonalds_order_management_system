package mc.donalds.service;

import java.util.List;

import mc.donalds.entity.ItemType;

public interface ItemTypeService {
	List<ItemType> getAllTypes();
	ItemType getType(int typeId);
	void saveType(ItemType itemType);
	void updateType(ItemType itemType);
	void deleteType(ItemType itemType);
}
