package mc.donalds.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mc.donalds.entity.Item;

@Component
@Scope("prototype")
public class Order {
	private int billId;
	private List<Item> orderList;
	private double totalCost;
	
	protected Order() {
	}
	
	public void addItem(Item item) {
		if (orderList == null)
			orderList = new ArrayList<Item>();

		if (item != null) {
			totalCost += item.getPrice();
			orderList.add(item);
		}
	}

	public List<Item> getOrderList() {
		if (orderList == null)
			orderList = new ArrayList<Item>();
		return orderList;
	}

	public double getTotalCost() {
		return totalCost;
	}

	protected void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Item item : orderList) {
			builder.append("item : " + item.getName() + " price: " + item.getPrice());
		}
		builder.append("\n");
		builder.append("Total price : " + totalCost);
		return builder.toString();
	}
}
