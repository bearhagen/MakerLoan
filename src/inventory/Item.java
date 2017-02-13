package inventory;

public class Item {
	private String name;
	private int ItemID;
	private int amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItemID() {
		return ItemID;
	}

	public void setItemID(int itemID) {
		ItemID = itemID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
