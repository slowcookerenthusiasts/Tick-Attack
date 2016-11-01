import java.util.TimerTask;

public class PlayerSCIncreaser extends TimerTask {
	
	Player activePlayer;
	
	/**
	 * Constructor, will take in a player.
	 * @param player is a Player object, whose street cred is to be increased.
	 */
	public PlayerSCIncreaser(Player player) {
		activePlayer = player;
	}
	
	/**
	 * This method will increase the player's street cred
	 */
	public void run() {
		activePlayer.increaseStreetCredBy(1);
	}
	

}
