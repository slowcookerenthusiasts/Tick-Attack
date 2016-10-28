
public class GameMain {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView("test", model);
		QuestController qController = new QuestController();
		GameController controller = new GameController(view, qController, model);
		controller.run();

	}

}
