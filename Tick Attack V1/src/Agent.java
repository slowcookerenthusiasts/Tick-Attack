public interface Agent {	
	/**
	 * An agent has a health, and the user should be able to get it
	 * @return an integer specifying the agent's health
	 */
	public int getHealth();
	
	/**
	 * An agent's health can change, and the user should be able to decrease it.
	 * @param value a number that the agent's health should decrease by
	 */
	public void decreaseHealthBy(int value);
	
	/**
	 * An agent's health can change, and the user should be able to increase it.
	 * @param value a number that the agent's health should increase by
	 */
	
	public void increaseHealthBy(int value);

}
