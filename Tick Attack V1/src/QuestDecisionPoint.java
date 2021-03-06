import java.util.*;

public class QuestDecisionPoint implements Cloneable {

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

	/**
	 * QuestDecisionPoint constructor	
	 * @param requiresChoice boolean indicating whether a player decision is required at the QuestDecisionPoint
	 * @param rewardsExist boolean indicating whether there is a health, street cred, or item effect associated 
	 * 		  with the QuestDecisionPoint.
	 * @param nodeText String indicating the text that should be displayed upon reaching the decision point.
	 */

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

	/**
	 * 	
	 * @param int effect representing the change in the player's health that should occur upon 
	 *        reaching the QuestDecisionPoint.
	 */

	public void setHealthEffect(int effect){
		hasHealthEffect = true;
		hasRewards = true;
		healthEffect = effect;
	}


	/**
	 * 
	 * @param int effect representing the change in the player's street cred that should occur upon 
	 *        reaching the QuestDecisionPoint.
	 */

	public void setSCEffect(int effect){
		hasSCEffect = true;
		hasRewards = true;
		sCEffect = effect;
	}

	/**
	 * 
	 * @param rewardItem item meant to be added to the player's inventory upon reaching the QuestDecisionPoint
	 */

	public void setItemReward(Item rewardItem){
		hasItemReward = true;
		hasRewards = true;
		itemReward = rewardItem;
	}


	/**
	 * 	The following method adds a child as a node to another QuestDecisionPoint, this represents a possible direction 
	 * a quest can move in from the QuestDecisionPoint the child is added to.
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
	 * 
	 * This method returns one of a nodes children in response to a player decision.
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

	/**
	 * generateChild() method
	 * 
	 * This method selects a child of the node in a quasi random fashion where nodes with higher weights are more
	 * likely to be chosen. 
	 */

	public QuestDecisionPoint generateChild(){
		if(!checkChildrenProbsExists()){
			return null;
		}
		int weightTotal= calculateWeightTotal();
		
		ArrayList<Double> childrenRelativeProbs = new ArrayList<Double>();
		ArrayList<Double> childrenCumRelativeProbs = new ArrayList<Double>();

		childrenRelativeProbs = fillRelativeProbs(childrenRelativeProbs, weightTotal);
		childrenCumRelativeProbs = fillCumRelativeProbs(childrenCumRelativeProbs, childrenRelativeProbs);
		
		return chooseRandomChild(childrenCumRelativeProbs);
	}
	
	/**
	 * A helper method, this method will choose a random child from an ArrayList of cumulative probabilities, with higher probabilities
	 * indicating that the child is more likely to be chosen.
	 * @param childrenCumRelativeProbs is an ArrayList of doubles, representing cumulative probabilities.
	 * @return A QuestDecisionPoint, which is the child randomly chosen.
	 */
	private QuestDecisionPoint chooseRandomChild(ArrayList<Double> childrenCumRelativeProbs) {
		Random generator = new Random();
		double pick = generator.nextDouble();

		for(int i=0; i<childrenCumRelativeProbs.size(); i++){
			if(pick <=childrenCumRelativeProbs.get(i)){
				return children.get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method will fill an ArrayList with cumulative probabilities, based on relative probabilities
	 * @param childrenCumRelativeProbs is the array list that is to be filled
	 * @param childrenRelativeProbs is the array list of relative probabilities
	 * @return A filled in ArrayList of doubles (indicating cumulative probabilities)
	 */
	private ArrayList<Double> fillCumRelativeProbs(ArrayList<Double> childrenCumRelativeProbs, ArrayList<Double> childrenRelativeProbs) {
		childrenCumRelativeProbs.add(0, childrenRelativeProbs.get(0));
		for(int i=1; i<childrenProbs.size(); i++){
			childrenCumRelativeProbs.add(i,childrenCumRelativeProbs.get(i-1)+childrenRelativeProbs.get(i));
		}
		
		return childrenCumRelativeProbs;
	}
	
	/**
	 * This method will fill an ArrayList with relative probabilities, based on a weight total.
	 * @param childrenRelativeProbs is the array list to be filled
	 * @param weightTotal is the weight total used to create relative probabilities
	 * @return a filled array list of doubles, indicating relative probabilities.
	 */
	private ArrayList<Double> fillRelativeProbs(ArrayList<Double> childrenRelativeProbs, int weightTotal) {
		for(int i=0; i<childrenProbs.size(); i++){
			childrenRelativeProbs.add(i, (((double) childrenProbs.get(i))/((double)weightTotal)));
		}
		
		return childrenRelativeProbs;
	}
	
	/**
	 * This method will calculate the weight total for a node's children
	 * @return an integer specifying the total weight
	 */
	private int calculateWeightTotal() {
		int weightTotal = 0;
		for(int i=0; i<childrenProbs.size(); i++){
			weightTotal = weightTotal+childrenProbs.get(i);
		}
		
		return weightTotal;
	}

	/**
	 * 	
	 * @return boolean indicating whether the arrest list of probabilities for a nodes children contains any elements.
	 */

	private boolean checkChildrenProbsExists() {
		if(childrenProbs.size() == 0){
			System.out.println("No children exist for the given node.");
			return false;
		} return true;
	}

	/**
	 * 
	 * @return String representing current quest state at the QuesdtDecisionPoint, meant to be seen by game player. 
	 */

	public String getDecisionText(){
		return defaultText;	
	}

	/**
	 * 
	 * @param boolean indicating whether the node should expect a player decision to select which of it's children 
	 *        should follow it in a quest. 
	 */

	public void takePlayerDecision(boolean choice ){
		playerDecisionNeeded = choice;
	}

	/**
	 * 	
	 * @return boolean indicating whether a player decision is needed at the QuestDecisionPoint, true indicates
	 *         a decision is needed, while false indicates a decision is not needed.
	 */

	public boolean getPlayerDecisionNeeded(){
		return playerDecisionNeeded;
	}

	/**
	 * 	
	 * @return boolean indicating whether the given QuestDecisionPoint has children.
	 */

	public boolean hasChildren(){
		return childrenExist;
	}


	/**
	 * 
	 * @return boolean indicating whether upon reaching the node an item should be added to the player's inventory.
	 */

	public boolean getHasItemReward(){
		return hasItemReward;
	}


	/**
	 * 
	 * @return boolean indicating whether the node is meant to change the player's health.
	 */


	public boolean getHasHealthEffect(){
		return hasHealthEffect;
	}

	/**
	 * 
	 * @return boolean indicating whether the node is meant to change the player's street cred.
	 */

	public boolean getHasSCEffect(){
		return hasSCEffect;
	}

	/**
	 * 
	 * @return item reward associated with the QuestDecisionPoint.
	 */

	public Item getItemReward(){
		return itemReward;
	}


	/**
	 * 
	 * @return int representing the change to the player's health meant to be associated with the
	 *         QuestDecisionPoint.
	 */

	public int getHealthEffect(){
		return healthEffect;
	}


	/**
	 * 
	 * @return int representing the change to the player's street cred meant to be associated with the
	 *         QuestDecisionPoint.
	 */

	public int getSCEffect(){
		return sCEffect;
	}	

}
