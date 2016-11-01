import java.util.*;

public class QuestDecisionPoint {

	private String defaultText;
	private ArrayList<QuestDecisionPoint> children = new ArrayList<QuestDecisionPoint>();
	private boolean playerDecisionNeeded;
	private ArrayList<Integer> childrenProbs = new ArrayList<Integer>();
	private boolean childrenExist;
	private boolean hasRewards;
	private boolean hasItemReward;
	private Item itemReward;
	private boolean hasHealthEffect;
	private int healthEffect;
	private boolean hasSCEffect;
	private int sCEffect;


	public QuestDecisionPoint(boolean requiresChoice, boolean rewardsExist, String nodeText){
		playerDecisionNeeded = requiresChoice;
		hasRewards = rewardsExist;
		defaultText = nodeText;
		hasRewards = false;
		hasItemReward = false;
		hasHealthEffect = false;
		hasSCEffect = false;
		childrenExist = false;
	}

	public void setHealthEffect(int effect){
		hasHealthEffect = true;
		hasRewards = true;
		healthEffect = effect;
	}

	public void setSCEffect(int effect){
		hasSCEffect = true;
		hasRewards = true;
		sCEffect = effect;
	}

	public void setItemReward(Item rewardItem){
		hasItemReward = true;
		hasRewards = true;
		itemReward = rewardItem;
	}
	
	/**
	 * 	
	 * @param child questDecisionPoint object to be added
	 * @param weight: a weight between 1 and 100 indicating how likely it should be that a given child occurs. 
	 */

	public void addChild(QuestDecisionPoint child,  int weight){
		childrenExist = true; 
		if(playerDecisionNeeded==true && children.size()>=2){
			System.out.println("A decision point that requires a user decision may only have two children");
			return;
		}//if
		children.add(child);
		childrenProbs.add(weight);

	}
	
	/**
	 * getChosenChild() method
	 * 
	 * @param choice: a boolean indicating whether the user has chosen yes or no when presented an option in game
	 * 				  yes corresponds to a choice with value true and indicates that the first of the QuestDecisionPoint's
	 * 				  children should be returned, while false indicates the last. 
	 */

	public QuestDecisionPoint getChosenChild(boolean choice){
		if(playerDecisionNeeded == false){
			System.out.println("This node does not specifify a player decision so a player choice cannot be returned.");
			return null;
		}
		if(choice){
			return children.get(0);
		}
		else{
			return children.get(1);
		}
	}

	//-----------------------------------------------------------------------------	
	/**
	 * generateChild() method
	 * 
	 * This method selects a child of the node in a quasi random fashion where nodes with higher weights are more
	 * likely to be choosen. 
	 */

	public QuestDecisionPoint generateChild(){
		if(!checkChildrenProbsExists()){
			return null;
		}
		
		int weightTotal= 0;
		ArrayList<Double> childrenRelativeProbs = new ArrayList<Double>();
		ArrayList<Double> childrenCumRelativeProbs = new ArrayList<Double>();
		
		for(int i=0; i<childrenProbs.size(); i++){
			weightTotal = weightTotal+childrenProbs.get(i);
		}
		
		for(int i=0; i<childrenProbs.size(); i++){
			childrenRelativeProbs.add(i, (((double) childrenProbs.get(i))/((double)weightTotal)));
		}
		childrenCumRelativeProbs.add(0, childrenRelativeProbs.get(0));
		for(int i=1; i<childrenProbs.size(); i++){
			childrenCumRelativeProbs.add(i,childrenCumRelativeProbs.get(i-1)+childrenRelativeProbs.get(i));
		}
		
		Random generator = new Random();
		double pick = generator.nextDouble();
		
		for(int i=0; i<childrenCumRelativeProbs.size(); i++){
			if(pick <=childrenCumRelativeProbs.get(i)){
				return children.get(i);
			}
		}
		return null;
	}
	
	private boolean checkChildrenProbsExists() {
		if(childrenProbs.size() == 0){
			System.out.println("No children exist for the given node.");
			return false;
		} return true;
	}

	public String getDecisionText(){
		return defaultText;	
	}

	public void takePlayerDecision(boolean choice ){
		playerDecisionNeeded = choice;
	}


	public boolean getPlayerDecisionNeeded(){
		return playerDecisionNeeded;
	}
	
	public boolean hasChildren(){
		return childrenExist;
	}


	public boolean getHasItemReward(){
		return hasItemReward;
	}

	public boolean getHasHealthEffect(){
		return hasHealthEffect;
	}

	public boolean getHasSCEffect(){
		return hasSCEffect;
	}

	public Item getItemReward(){
		return itemReward;
	}

	public int getHealthEffect(){
		return healthEffect;
	}

	public int getSCEffect(){
		return sCEffect;
	}	
}
