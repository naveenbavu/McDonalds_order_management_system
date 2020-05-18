package mc.donalds.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.donalds.entity.Item;

@Repository
public class ItemDAOImpl implements ItemDAO {

	@Autowired
	private SessionFactory sessionFactory; // ovde treba da bude hibernate

	@Override
	public List<Item> getAllItems() {
		return getSession().createQuery("from Item", Item.class).getResultList();
	}

	@Override
	public Item getItem(int id) {
		return getSession().get(Item.class, id);
	}

	@Override
	public Item getItem(String name) {
		return getSession().createQuery("FROM Item WHERE name = :name", Item.class)
									.setParameter("name", name)
									.getSingleResult();
	}

	@Override
	public void saveItem(Item item) {
		Session session = getSession();
		session.save(item);
	}

	@Override
	public void updateItem(Item item) {
		Session session = getSession();
		session.update(item);
	}

	@Override
	public void deleteItem(Item item) {
		Session session = getSession();
		session.delete(item);
	}

	private Session getSession() { // i ovde treba hibernate
		return sessionFactory.getCurrentSession();
	}

}
