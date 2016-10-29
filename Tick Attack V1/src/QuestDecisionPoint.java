import java.util.*;

public class QuestDecisionPoint {
	
//-----------------------------------------------------------------------------
/**
 * Instance variables	
 */
	
	private Object questType; //????
	private String defaultText;
	private ArrayList<QuestDecisionPoint> children = new ArrayList<QuestDecisionPoint>();
	private boolean playerDecisionNeeded;
	private ArrayList<QuestDecisionPoint> childrenProbs = new ArrayList<QuestDecisionPoint>();
	private boolean hasItemReward;
	private Item itemReward;
	private boolean hasHealthEffect;
	private int healthEffect;
	private boolean hasSCEffect;
	private int sCEffect;
	
//-----------------------------------------------------------------------------	
/**
 * generateChild() method
 * 
 * This method 
 */
	
	public QuestDecisionPoint generateChild(){
		//method to be filled
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
