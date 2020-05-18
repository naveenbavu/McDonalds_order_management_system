package mc.donalds.dao;

import java.time.LocalDate;
import java.util.List;

import mc.donalds.entity.Bill;

public interface BillDAO {
	
	Bill getBill(int billId);
	
	List<Bill> getAll();
	
	int saveBill(Bill bill);
	
	void removeLastBill();
	
	void updateBillPayed(int billId);
	
	List<Bill> getBillsFromDate(LocalDate from, LocalDate to);
	
}
