package inventory;

public class Item {
	private String name;
	private int itemID;
	private int amount;
	
	// If only name is given, assume only 1 amount.
	public Item(String name) {
		this.name = name;
		this.amount = 1;
	}
	
	public Item(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public Item(String name, int amount, int itemID) {
		this(name, amount);
		this.itemID = itemID;
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
}
