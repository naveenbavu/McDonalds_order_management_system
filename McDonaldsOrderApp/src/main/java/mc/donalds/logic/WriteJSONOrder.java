package mc.donalds.logic;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mc.donalds.exceptions.OrderExistsException;

@Component
public class WriteJSONOrder {
	private final String FILEURI = "JSONOrders/";

	public boolean saveOrder(Order order) {
		File file = null;
		boolean saved = true;
		
		try {
			prepareFolder();
			file = prepareFile(order.getBillId());
		} catch (OrderExistsException e) {
			System.out.println("Order not saved");
			saved = false;
		}

		if (saved) {
			try {
				new ObjectMapper().writeValue(file, order);
			} catch (IOException e) {
				saved = false;
			}
		}

		return saved;
	}

	private File prepareFile(int fileName) throws OrderExistsException {
		File file = new File(FILEURI + fileName + ".json");
		if(file.exists())
			throw new OrderExistsException();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	private void prepareFolder() {
		File folder = new File(FILEURI);
		if(!folder.exists())
			folder.mkdir();
	}
}
