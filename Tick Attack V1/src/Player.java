import java.util.TimerTask;

public class Player extends TimerTask {
	
	int streetCred;
	int health;
	
	public Player() {
		streetCred = 0;
		health = 100;
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
