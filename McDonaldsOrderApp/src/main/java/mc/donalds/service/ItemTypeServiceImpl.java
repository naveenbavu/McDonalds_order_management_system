package mc.donalds.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.donalds.dao.ItemTypeDAO;
import mc.donalds.entity.ItemType;

@Repository
public class ItemTypeServiceImpl implements ItemTypeService {

	@Autowired
	private ItemTypeDAO itemTypeDAO;
	
	@Override
	@Transactional
	public List<ItemType> getAllTypes() {
		return itemTypeDAO.getAllTypes();
	}

	@Override
	@Transactional
	public ItemType getType(int typeId) {
		return itemTypeDAO.getItemType(typeId);
	}

	@Override
	@Transactional
	public void saveType(ItemType itemType){
		if(itemType != null) {
			ItemType checkType = null;
			if(itemType.getId() > 0) {
				checkType = itemTypeDAO.getItemType(itemType.getId());
			} else {
				checkType = checkIfItemExists(itemType.getTypeName());
			}
			if(checkType == null) {
				itemTypeDAO.saveItemType(itemType);
			}
		}
	}

	@Override
	@Transactional
	public void updateType(ItemType itemType){
		if(itemType != null && itemType.getId() > 0) {
			ItemType checkType = null;
			if(itemType.getId() > 0) {
				checkType = itemTypeDAO.getItemType(itemType.getId());
			} else {
				checkType = checkIfItemExists(itemType.getTypeName());
			}
			if(checkType.equals(itemType)) {
				itemTypeDAO.updateItemType(itemType);
			}
		} 
	}

	@Override
	@Transactional
	public void deleteType(ItemType itemType){
		if(itemType != null && itemType.getId() > 0) {
			ItemType checkType = itemTypeDAO.getItemType(itemType.getId());
			if(checkType != null) {
				itemTypeDAO.deleteItemType(itemType);
			}
		}
	}
	
	private ItemType checkIfItemExists(String name) {
		try {
			return itemTypeDAO.getItemType(name);
		} catch (NoResultException ex) {
			return null;
		}
	}
}
