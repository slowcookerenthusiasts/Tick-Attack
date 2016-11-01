
public class Agent {
	
	protected int health;
	
	/**
	 * Getter, will
	 * @return the agent's health (as an integer)
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * This method will decrease health by a value specified by the user.
	 * @param value is an integer, indicating how much the agent's health should decrease by.
	 */
	public void decreaseHealthBy(int value) {
		health = health - value;
	}
	
	/**
	 * This method will increase health by a value specified by the user.
	 * @param value is an integer, indicating how much the agent's health should increase by.
	 */
	public void increaseHealthBy(int value) {
		health = health + value;
	}

	


}
