import java.util.*;

public class QuestDecisionPoint implements Cloneable {
	
//-----------------------------------------------------------------------------
/**
 * Instance variables	
 */
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
	
//-----------------------------------------------------------------------------	
	
	public QuestDecisionPoint(boolean requiresChoice, boolean rewardsExist, String nodeText){
		playerDecisionNeeded = requiresChoice;
		hasRewards = rewardsExist;
		defaultText = nodeText;
		hasRewards = false;
		hasItemReward = false;
		hasHealthEffect = false;
		hasSCEffect = false;
		childrenExist = false;
	
	}//DecisionPoint constructor
	
//-----------------------------------------------------------------------------

	public QuestDecisionPoint clone() throws CloneNotSupportedException{
		return (QuestDecisionPoint)super.clone();
	}//clone() method
	
//-----------------------------------------------------------------------------	
	
	public void setHealthEffect(int effect){
		hasHealthEffect = true;
		hasRewards = true;
		healthEffect = effect;
	}//setItemReward() method
	
//-----------------------------------------------------------------------------	
	
	public void setSCEffect(int effect){
		hasSCEffect = true;
		hasRewards = true;
		sCEffect = effect;
	}//setItemReward() method
	
//-----------------------------------------------------------------------------	
	
	public void setItemReward(Item rewardItem){
		hasItemReward = true;
		hasRewards = true;
		itemReward = rewardItem;
	}//setItemReward() method

//-----------------------------------------------------------------------------	
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
		
	}//addChild() method
	
//-----------------------------------------------------------------------------	
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
		}//if
		else{
			return children.get(1);
		}//else
	}
	
//-----------------------------------------------------------------------------	
/**
 * generateChild() method
 * 
 * This method selects a child of the node in a quasi random fashion where nodes with higher weights are more
 * likely to be choosen. 
 */
	
	public QuestDecisionPoint generateChild(){
		if(childrenProbs.size() == 0){
			System.out.println("No children exist for the given node.");
			return null;
		}
		int weightTotal= 0;
		ArrayList<Double> childrenRelativeProbs = new ArrayList<Double>();
		ArrayList<Double> childrenCumRelativeProbs = new ArrayList<Double>();
		for(int i=0; i<childrenProbs.size(); i++){
			weightTotal = weightTotal+childrenProbs.get(i);
		}//for
		for(int i=0; i<childrenProbs.size(); i++){
			childrenRelativeProbs.add(i, (((double) childrenProbs.get(i))/((double)weightTotal)));
		}//for
		childrenCumRelativeProbs.add(0, childrenRelativeProbs.get(0));
		for(int i=1; i<childrenProbs.size(); i++){
			childrenCumRelativeProbs.add(i,childrenCumRelativeProbs.get(i-1)+childrenRelativeProbs.get(i));
		}//for
		Random generator = new Random();
		double pick = generator.nextDouble();
		int pickIndex;
		for(int i=0; i<childrenCumRelativeProbs.size(); i++){
			if(pick <=childrenCumRelativeProbs.get(i)){
				return children.get(i);
			}//if
		}//for
		return null;
	}//generateChild() method
	
//-----------------------------------------------------------------------------	
/**
 * getDecisionText() method	
 */
	
	public String getDecisionText(){
		return defaultText;	
	}//getDecisionText() method
	
//-----------------------------------------------------------------------------	
/**
 * takePlayerDecision() method	
 */
	
	public void takePlayerDecision(boolean choice ){
		playerDecisionNeeded = choice;
	}//takePlayerDecision() method
	
//-----------------------------------------------------------------------------	
/**
 * getPlayerDecisionNeeded() method	
 */
	
	public boolean getPlayerDecisionNeeded(){
		return playerDecisionNeeded;
	}//getPlayerDecisionNeeded
	
//-----------------------------------------------------------------------------	
	
	public boolean hasChildren(){
		return childrenExist;
	}//hasChildren(0 method
	
//-----------------------------------------------------------------------------	
/**
 * getHasItemReward() method
 */
	
	public boolean getHasItemReward(){
		return hasItemReward;
	}//getHasItemReward()
	
//-----------------------------------------------------------------------------	
	/**
	 * getHasHealthEffect() method
	 */
		
		public boolean getHasHealthEffect(){
			return hasHealthEffect;
		}//getHasItemReward()
		
//-----------------------------------------------------------------------------
	/**
	 *  getHasItemReward() method
	 */
			
		public boolean getHasSCEffect(){
			return hasSCEffect;
		}//getHasItemReward()
			
//-----------------------------------------------------------------------------	
	/**
	 * getItemReward() method	
	 */
		
		public Item getItemReward(){
			return itemReward;
		}//getItemReward
		
//-----------------------------------------------------------------------------
	/**
	 * getHealthEffect() method	
	 */
			
		public int getHealthEffect(){
			return healthEffect;
		}//getItemReward
			
//-----------------------------------------------------------------------------	
	/**
	 * getHealthEffect() method	
	 */
				
		public int getSCEffect(){
			return sCEffect;
		}//getItemReward
				
//-----------------------------------------------------------------------------	
		
}//QuestDecisionPoint
