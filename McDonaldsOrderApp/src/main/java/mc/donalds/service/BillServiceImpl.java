package mc.donalds.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mc.donalds.dao.BillDAO;
import mc.donalds.entity.Bill;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDAO billDAO;

	@Override
	@Transactional
	public Bill getBill(int billId) {// ovaj moze da se koristi u u toku pretrage racuna
		return billDAO.getBill(billId);
	}

	@Override
	@Transactional
	public int saveBill(Bill bill){// ovaj moze da se koristi u OrderServceImpl
		int billId = -1;
		if (bill != null && bill.getBillId() == 0) {
			billId = billDAO.saveBill(bill);
		}
		return billId;
	}

	@Override
	@Transactional
	public void removeLastBill() {// ovaj moze da se koristi u OrderServceImpl
		billDAO.removeLastBill();
	}

	@Override
	@Transactional
	public void updateBillPayed(int billId) {// ovaj moze da se koristi kada se uplata
		billDAO.updateBillPayed(billId);
	}

	@Override
	@Transactional
	public List<Bill> getAllBills() {
		return billDAO.getAll();
	}

	@Override
	@Transactional
	public List<Bill> getBillsFrom(LocalDate from) {
		return billDAO.getBillsFromDate(from, LocalDate.now());
	}
}
