package inventory;

import java.util.ArrayList;

public class Item {
	private String name;
	private int itemID;
	private int amount;
	private static ArrayList<Item> items = new ArrayList<Item>();
	
	public Item(String name, int itemID) {
		this.name = name;
		this.amount = amount;
		this.itemID = itemID;
		items.add(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public static ArrayList<Item> getItems() {
		return items;
	}
}
