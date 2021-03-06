import java.util.ArrayList;

public class Player extends  Agent {
	
	private int streetCred;
	private ArrayList<Item> inventory;
	
	/**
	 * Constructor, will initialize the player's street cred to be 0 and health to
	 * be 100.
	 */
	public Player() {
		streetCred = 0;
		health = 100;
		inventory = new ArrayList<Item>();
	}
	
	/**
	 * Setter, will set the player's health
	 * @param value is an integer indicating the new health
	 */
	public void setHealth(int value) {
		health = value;
	}
	
	/**
	 * This method will add items to the player's inventory.
	 * @param item is an Item object
	 */
	public void addToInventory(Item item) {
		inventory.add(item);
	}
	
	/**
	 * This method will case the player to use an item, making it leave the person's
	 * inventory.
	 * @param item is an Item object the player uses.
	 */
	public void useItem(Item item) {
		inventory.remove(item);
	}
	
	/**
	 * This method will return the player's inventory.
	 * @return an ArrayList of Items, representing the player's inventory.
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * This method will increase street cred by a value specified by the user.
	 * @param value is an integer, indicating how much the player's street cred should increase by.
	 */
	public void increaseStreetCredBy(int value) {
		streetCred = streetCred + value;
	}
	
	/**
	 * This method will decrease  street cred by a value specified by the user.
	 * @param value is an integer, indicating how much the player's street cred should decrease by.
	 */
	
	public void decreaseStreetCredBy(int value) {
		streetCred = streetCred - value;
	}
	
	/**
	 * Getter, will
	 * @return the player's street cred (as an integer)
	 */
	public int getStreetCred() {
		return streetCred;
	}
	
}
