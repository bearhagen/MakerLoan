package inventory;

import java.util.ArrayList;

import hr.Person;

public class Item {
	private String name;
	private int itemID;
	private static ArrayList<Item> items = new ArrayList<Item>();
	
	public Item(String name, int itemID) {
		this.name = name;
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
	
	public static ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 *  Check if this loan is already created
	 */
	public static boolean exist(int itemID) {
		for (int i = 0; i < items.size(); i++) {
			Item tmpItem = items.get(i);
			if	(tmpItem.getItemID() == itemID) {
				return true;
			}
		}
		
		return false;
	}
		
	/**
	 *  Get values and turn them into a human friendly string
	 */
	@Override
	public String toString() {
		return itemID + " - " + name;
	}
	
	/**
	 *  Runs toString method on all the items in the item list and returns them in an array
	 */
	public static Object[] toStrings() {
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < items.size(); i++) {
			strings.add(items.get(i).toString());
		}
		
		return strings.toArray();
	}
}
