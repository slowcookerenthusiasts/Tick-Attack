import java.util.TimerTask;
public class PlayerHealthIncreaser extends TimerTask{

	Player activePlayer;
	
	/**
	 * Constructor, will take in a player.
	 * @param player is a Player object, whose health is to be decreased.
	 */
	public PlayerHealthIncreaser(Player player) {
		activePlayer = player;
	}
	
	/**
	 * This method will decrease the player's health by 1.
	 */
	public void run() {
		activePlayer.decreaseHealthBy(1);
	}
	

}
