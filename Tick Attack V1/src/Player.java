import java.util.ArrayList;
import java.util.TimerTask;

public class Player extends TimerTask {
	
	private int streetCred;
	private int health;
	private ArrayList<Item> inventory;
	
	public Player() {
		streetCred = 0;
		health = 100;
		inventory = new ArrayList<Item>();
	}
	
	public void addToInventory(Item item) {
		inventory.add(item);
	}
	
	public void useItem(Item item) {
		inventory.remove(item);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void run() {
		increaseStreetCred();
	}
	
	public void increaseStreetCred() {
		streetCred++;
	}
	
	public void increaseStreetCredBy(int value) {
		streetCred = streetCred + value;
	}
	
	public void decreaseStreetCredBy(int value) {
		streetCred = streetCred - value;
	}
	
	public int getStreetCred() {
		return streetCred;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void decreaseHealthBy(int value) {
		health = health - value;
	}
	
	public void increaseHealthBy(int value) {
		health = health + value;
	}

}
