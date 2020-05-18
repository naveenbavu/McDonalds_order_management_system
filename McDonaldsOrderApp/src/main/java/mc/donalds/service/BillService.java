package mc.donalds.service;

import java.time.LocalDate;
import java.util.List;

import mc.donalds.entity.Bill;

public interface BillService {

	Bill getBill(int billId);

	int saveBill(Bill bill);
	
	void removeLastBill();
	
	void updateBillPayed(int billId);

	List<Bill> getAllBills();

	List<Bill> getBillsFrom(LocalDate of);

}
