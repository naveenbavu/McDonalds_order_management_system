package mc.donalds.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mc.donalds.entity.ItemType;

@Repository
public class ItemTypeDAOImpl implements ItemTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ItemType> getAllTypes() {
		return getSession().createQuery("from ItemType", ItemType.class).getResultList();
	}

	@Override
	public ItemType getItemType(int id) {
		return getSession().get(ItemType.class, id);
	}

	@Override
	@Transactional
	public ItemType getItemType(String typeName) {
		return getSession().createQuery("FROM ItemType WHERE typeName = :name", ItemType.class)
				.setParameter("name", typeName).uniqueResult();
	}

	@Override
	public void saveItemType(ItemType itemType) {
		Session session = getSession();
		session.save(itemType);
	}

	@Override
	public void updateItemType(ItemType itemType) {
		Session session = getSession();
		session.update(itemType);
	}

	@Override
	public void deleteItemType(ItemType itemType) {
		Session session = getSession();
		session.delete(itemType);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
