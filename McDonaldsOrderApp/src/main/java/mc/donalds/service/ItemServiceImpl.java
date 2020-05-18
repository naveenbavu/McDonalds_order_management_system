package mc.donalds.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.donalds.dao.ItemDAO;
import mc.donalds.entity.Item;

@Repository
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	@Override
	@Transactional
	public Item getItem(int id) {
		return itemDAO.getItem(id);
	}

	@Override
	@Transactional
	public List<Item> getAllItems() {
		return itemDAO.getAllItems();
	}

	@Override
	@Transactional
	public void saveItem(Item item){
		if(item != null) {
			Item checkItem = null;
			if(item.getId() > 0) {
				checkItem = itemDAO.getItem(item.getId());
			} else {
				checkItem = checkIfItemExists(item.getName());
			}
			if(checkItem == null) {
				itemDAO.saveItem(item);
			}
		}
	}

	@Override
	@Transactional
	public void updateItem(Item item){
		if(item != null) {
			Item checkItem = null;
			if(item.getId() > 0) {
				checkItem = itemDAO.getItem(item.getId());
			} else {
				checkItem = checkIfItemExists(item.getName());
			}
			if(item.equals(checkItem)) {
				itemDAO.updateItem(item);
			}
		}
	}

	@Override
	@Transactional
	public void deleteItem(Item item){
		if(item != null) {
			Item checkItem = null;
			if(item.getId() > 0) {
				checkItem = itemDAO.getItem(item.getId());
			}
			if(item.equals(checkItem)) {
				itemDAO.deleteItem(item);
			}
		}
	}
	
	private Item checkIfItemExists(String name) {
		try {
			return itemDAO.getItem(name);
		} catch (NoResultException ex) {
			return null;
		}
	}
}
