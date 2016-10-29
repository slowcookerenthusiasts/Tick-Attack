
public class GameMain {

	public static void main(String[] args) {
		Player player = new Player();
		player.addToInventory(new Item("Cellphone", 0));
		player.addToInventory(new Item("Lipstick", 0));
		
		QuestController qController = new QuestController();
		GameModel model = new GameModel(player);
		GameView view = new GameView("test", qController);
		
		GameController controller = new GameController(view, qController, model);
			
		controller.runGame();
	}

}
