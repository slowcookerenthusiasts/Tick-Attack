import java.util.TimerTask;


public class PlayerHealth extends TimerTask{

	Player activePlayer;
	
	public PlayerHealth(Player player) {
		activePlayer = player;
	}
	
	public void run() {
		activePlayer.decreaseHealthBy(1);
	}
	

}
