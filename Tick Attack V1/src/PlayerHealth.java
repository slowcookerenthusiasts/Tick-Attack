import java.util.TimerTask;
public class PlayerHealth extends TimerTask{

	Player activePlayer;
	
	/**
	 * Constructor, will take in a player.
	 * @param player is a Player object, whose health is to be decreased.
	 */
	public PlayerHealth(Player player) {
		activePlayer = player;
	}
	
	/**
	 * This method will decrease the player's health by 1.
	 */
	public void run() {
		activePlayer.decreaseHealthBy(1);
	}
	

}
