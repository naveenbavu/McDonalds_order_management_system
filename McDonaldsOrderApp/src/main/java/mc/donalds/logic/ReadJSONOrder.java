package mc.donalds.logic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import mc.donalds.entity.Item;

@Component
public class ReadJSONOrder {

	private final String FILEURI = "JSONOrders/";

	public Order getOrder(int orderId) {
		JSONParser parser = new JSONParser();
		Order order = null;

		try (FileReader reader = new FileReader(FILEURI + orderId + ".json")) {
			JSONObject obj = (JSONObject) parser.parse(reader);
			order = new Order();
			order.setBillId(orderId);

			JSONArray orderList = (JSONArray) obj.get("orderList");
			
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = orderList.iterator();

			while (iterator.hasNext()) {
				order.addItem(readItem(iterator.next()));
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return order;
	}

	private Item readItem(JSONObject itemObj) {
		Item item = new Item();

		item.setName((String) itemObj.get("name"));
		item.setPrice(Double.parseDouble(itemObj.get("price").toString()));

		return item;
	}

}
