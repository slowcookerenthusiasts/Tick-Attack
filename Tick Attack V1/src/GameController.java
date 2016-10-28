import java.util.Timer;
import java.util.TimerTask;


public class GameController{

	private GameView view;
	private QuestController questController;
	private GameModel gameModel;

	public GameController(GameView gView, QuestController qController, GameModel gModel) {
		view = gView;
		questController = qController;
		gameModel = gModel;
	}

	public void createQuest() {

	}

	public void deleteQuest() {

	}

	public boolean isOver() {

		for(int i = 0; i < 4500; i++) {

		}

		return true;
	}

	public void run() {	
		Player player = gameModel.getPlayer();
		Timer t = new Timer();
		t.schedule(player, 0, 1000);
		while(true) {
			view.showStreetCred(player.getStreetCred());
		}
	}

	public void runQuest() {

	}

	public void updateModel() {

	}

	public void updateView() {

	}

}
