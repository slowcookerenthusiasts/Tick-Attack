import java.util.*;
public class GameModel {

	private ArrayList<QuestDecisionPoint> questDirectory = new ArrayList<QuestDecisionPoint>();
	private  QuestDecisionPoint activeQuest;
	private Player player;
	
	public void setPlayer(Player inputPlayer) {
		player = inputPlayer;
	}
	
	public void setActiveQuest(QuestDecisionPoint inputActiveQuest) {
		activeQuest = inputActiveQuest;
	}
	
	public void addQuest(QuestDecisionPoint inputQuest) {
		questDirectory.add(inputQuest);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public QuestDecisionPoint getActiveQuest() {
		return activeQuest;
	}
	
	public ArrayList<QuestDecisionPoint> getQuestDirectory() {
		return questDirectory;
	}
	
 }
