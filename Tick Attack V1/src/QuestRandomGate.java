import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Mathieu Belzile-Ha
 *
 * Wrapper something class that holds multiple node identities and only returns one.
 *
 *TODO: clean up with a Quest interface or something
 *
 */
public class QuestRandomGate extends QuestDecisionPoint{
	
	private ArrayList<QuestDecisionPoint> possibleIdentities;
	private ArrayList<Double> identitySelectionMap;
	private QuestDecisionPoint currentIdentity;
	private Condition conditionOfPassage;
	
	Random random;
	
	/**
	 * TODO: fix how messy this constructor is (basically filling in unnecessary fields)
	 * @param requiresChoice
	 * @param rewardsExist
	 * @param nodeText
	 */
	
	public QuestRandomGate(ArrayList<QuestDecisionPoint> possibleIdentities, ArrayList<Double> possibleIdentitiesWeights, Condition conditionOfPassage) {
		super(false, false, null);
		
		this.random = new Random();
		
		this.conditionOfPassage = conditionOfPassage;
		this.possibleIdentities = possibleIdentities;
		this.identitySelectionMap = generateSelectionMap(possibleIdentitiesWeights);
		this.currentIdentity = randomSelectIdentity();
	}
	
	private QuestDecisionPoint randomSelectIdentity(){
		double lowerBound = 0;
		double upperBound = 0;
		
		double roll = random.nextDouble();
		
		for (int i = 0; i < identitySelectionMap.size(); i++){
			upperBound = identitySelectionMap.get(i);
			if (lowerBound <= roll && roll < upperBound)
				return possibleIdentities.get(i);
			lowerBound = upperBound;
		}
		
		return null;
	}
	
	private ArrayList<Double> generateSelectionMap(ArrayList<Double> weightDistribution) {
		ArrayList<Double> selectionMap = new ArrayList<Double>();
		double cumulativeWeight = 0;
		
		for (Double weight : weightDistribution){
			selectionMap.add(weight + cumulativeWeight);
			cumulativeWeight += weight;
		}

		selectionMap = normalize(selectionMap,cumulativeWeight);
		return selectionMap;
	}
	
	private ArrayList<Double> normalize(ArrayList<Double> input, double total){
		ArrayList<Double> output = new ArrayList<Double>();
		
		for (Double val : input){
			output.add(val/total);
		}
		
		return output;
	}
	
	@Override
	public QuestDecisionPoint getChosenChild(boolean choice){
		return currentIdentity.getChosenChild(choice);
	}
	
	@Override
	public QuestDecisionPoint generateChild(){
		return currentIdentity.generateChild();
	}
	
	public QuestDecisionPoint getChild(){
		if (this.conditionOfPassage.met())
			return generateChild();
		this.currentIdentity = randomSelectIdentity();
		return this;
	}

	@Override
	public String getDecisionText(){
		return currentIdentity.getDecisionText();
	}

	@Override
	public void takePlayerDecision(boolean choice ){
		currentIdentity.takePlayerDecision(choice);
	}

	/**
	 * 	
	 * @return boolean indicating whether a player decision is needed at the QuestDecisionPoint, true indicates
	 *         a decision is needed, while false indicates a decision is not needed.
	 */

	@Override
	public boolean getPlayerDecisionNeeded(){
		return currentIdentity.getPlayerDecisionNeeded();
	}

	/**
	 * 	
	 * @return boolean indicating whether the given QuestDecisionPoint has children.
	 */

	@Override
	public boolean hasChildren(){
		if (this.conditionOfPassage.met())
			return super.hasChildren();
		return true;
	}


	/**
	 * 
	 * @return boolean indicating whether upon reaching the node an item should be added to the player's inventory.
	 */

	@Override
	public boolean getHasItemReward(){
		return currentIdentity.getHasItemReward();
	}


	/**
	 * 
	 * @return boolean indicating whether the node is meant to change the player's health.
	 */

	@Override
	public boolean getHasHealthEffect(){
		return currentIdentity.getHasHealthEffect();
	}

	/**
	 * 
	 * @return boolean indicating whether the node is meant to change the player's street cred.
	 */

	@Override
	public boolean getHasSCEffect(){
		return currentIdentity.getHasSCEffect();
	}

	/**
	 * 
	 * @return item reward associated with the QuestDecisionPoint.
	 */

	@Override
	public Item getItemReward(){
		return currentIdentity.getItemReward();
	}


	/**
	 * 
	 * @return int representing the change to the player's health meant to be associated with the
	 *         QuestDecisionPoint.
	 */

	@Override
	public int getHealthEffect(){
		return currentIdentity.getHealthEffect();
	}


	/**
	 * 
	 * @return int representing the change to the player's street cred meant to be associated with the
	 *         QuestDecisionPoint.
	 */

	@Override
	public int getSCEffect(){
		return currentIdentity.getSCEffect();
	}
	
	public boolean equals(Object o){
		if (!super.equals(o))
			return false;
		if (!o.getClass().isAssignableFrom(QuestRandomGate.class))
			return false;
		
		QuestRandomGate qrg = (QuestRandomGate) o;
		
		if ((this.possibleIdentities.size() != qrg.possibleIdentities.size()) || (this.identitySelectionMap.size() != qrg.identitySelectionMap.size()))
			return false;
		
		for (int i = 0; i < this.possibleIdentities.size(); i++){
			if (!this.possibleIdentities.get(i).equals(qrg.possibleIdentities.get(i)))
				return false;
			if (!this.identitySelectionMap.get(i).equals(qrg.possibleIdentities.get(i)))
				return false;
		}
		
		if (!this.conditionOfPassage.equals(qrg.conditionOfPassage))
			return false;
		
		return true;
	}
	
}
