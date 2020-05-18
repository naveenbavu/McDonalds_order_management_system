package mc.donalds.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mc.donalds.entity.Item;
import mc.donalds.entity.ItemType;
import mc.donalds.exceptions.DatabaseAccessException;
import mc.donalds.exceptions.ItemException;
import mc.donalds.facade.AdminFacade;
import mc.donalds.facade.CustomerFacade;
import mc.donalds.logic.Order;
import mc.donalds.springConfig.SpringConfig;

public class App {
	public static void main(String[] args) throws DatabaseAccessException, ItemException {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AdminFacade af = context.getBean(AdminFacade.class);
		CustomerFacade cf = context.getBean(CustomerFacade.class);
		List<Item> items = new ArrayList<Item>();

		System.out.println("Input types (press x when finished)");
		try {
//			System.out.println("Item type input : ");
//			System.out.print("Type name : ");
//			while (true) {
//				String name = br.readLine();
//				if (name.equals("x"))
//					break;
//				if (name.isEmpty() || name.isBlank()) {
//					System.out.println("Name can not be empty or blank... Try again . . .");
//					continue;
//				}
//
//				System.out.print("Type decription : ");
//				String description = br.readLine();
//				System.out.println();
//				af.addNewItemType(new ItemType(name, description));
//				System.out.print("Type name or 'x' to exit : ");
//			}
//
//			System.out.println("Item input : ");
//			System.out.print("Item name : ");
//			while (true) {
//				String name = br.readLine();
//				if (name.equals("x"))
//					break;
//				System.out.print("Item price : ");
//				String p = br.readLine();
//				int price = 0;
//				try {
//					price = Integer.parseInt(p);
//				} catch (NumberFormatException nfe) {
//					System.out.println("Price can contain only numbers");
//				}
//
//				System.out.print("Item is of type? : ");
//				String type = br.readLine();
//				int typeId = Integer.parseInt(type);
//				ItemType it = af.getItemType(typeId);
//
//				af.addNewItem(new Item(name, price, 1, it, ""));
//				System.out.print("Item name or 'x' to exit : ");
//			}

			items = cf.getItems();
			System.out.println("Now you can make an order :)");
			System.out.println("Choose items to add to the order (press 'x' to finish)");
			System.out.println();
			for (Item item : items) {
				System.out.println(
						item.getId() + " - " + item.getName() + "    |    price : " + item.getPrice() + "    |");
			}
			
			System.out.println();

			Order order = cf.getOrder();
			while (true) {
				String input = br.readLine();
				if (input.equals("x"))
					break;
				try {
					int itemId = Integer.parseInt(input);
					order.addItem(items.get(itemId));
				} catch (NumberFormatException er) {
				}
			}

			System.out.println("Your order is now being proccessed :D");
			int orderId = cf.processOrder(order);
			System.out.println(orderId);
			System.out.println("Your order is : ");
			System.out.println(cf.peekOrder(orderId));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
