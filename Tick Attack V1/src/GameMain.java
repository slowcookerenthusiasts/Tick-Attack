
public class GameMain {

	public static void main(String[] args) {
		Player player = new Player();
		GameModel model = new GameModel(player);
		GameView view = new GameView("test");
		QuestController qController = new QuestController();
		GameController controller = new GameController(view, qController, model);
		controller.run();

	}

}
