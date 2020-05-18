package mc.donalds.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.donalds.entity.Bill;

@Repository
public class BillDAOImpl implements BillDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Bill getBill(int billId) {
		return getSession().get(Bill.class, billId);
	}

	@Override
	public List<Bill> getAll() {
		return getSession().createQuery("from Bill", Bill.class).getResultList();
	}

	@Override
	public void removeLastBill() {
		Session session = getSession();
		Bill bill = session.createQuery("FROM Bill ORDER BY fillId DESC LIMIT 1", Bill.class).uniqueResult();
		session.remove(bill);
	}

	@Override
	public int saveBill(Bill bill) {
		return (int) getSession().save(bill);
	}

	@Override
	public void updateBillPayed(int billId) {
		getSession().get(Bill.class, billId).setPayed(true);
	}

	@Override
	public List<Bill> getBillsFromDate(LocalDate fromDate, LocalDate toDate) {
		return getSession().createQuery("FROM Bill WHERE date BETWEEN :fromDate AND :toDate", Bill.class)
							.setParameter("fromDate", fromDate)
							.setParameter("toDate", toDate)
							.getResultList();
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
