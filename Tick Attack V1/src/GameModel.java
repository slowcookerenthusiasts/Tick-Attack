import java.util.*;
public class GameModel {

	private ArrayList<QuestDecisionPoint> questDirectory = new ArrayList<QuestDecisionPoint>();
	private  QuestDecisionPoint activeQuest;
	private Player player;

	/**
	 * Constructor, the game model is instantiated with a player object.
	 * @param inputPlayer is a Player object
	 */
	public GameModel(Player inputPlayer) {
		player = inputPlayer;
	}

	/**
	 * This method will update the model's active quest (the quest currently being
	 * played) to be the one specified by the user.
	 * @param inputActiveQuest is a QuestDecisionPoint that stores the quest data as a tree
	 */
	public void setActiveQuest(QuestDecisionPoint inputActiveQuest) {
		activeQuest = inputActiveQuest;
	}

	/**
	 * This method will add a quest to the model's quest directory.
	 * @param inputQuest is a QuestDecisionPoint that stores the quest data as a tree
	 */
	public void addQuest(QuestDecisionPoint inputQuest) {
		questDirectory.add(inputQuest);
	}

	/**
	 * Getter method,
	 * @return the Player of the game.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Getter method,
	 * @return the current active quest (as a QuestDecisionPoint)
	 */
	public QuestDecisionPoint getActiveQuest() {
		return activeQuest;
	}

	/**
	 * Getter method,
	 * @return an ArrayList of QuestDecisionPoints that represents the model's current
	 * quest directory.
	 */
	public ArrayList<QuestDecisionPoint> getQuestDirectory() {
		return questDirectory;
	}



}
