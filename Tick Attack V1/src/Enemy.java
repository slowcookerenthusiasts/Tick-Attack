public class Enemy implements Agent{
	
	private int health;
	private String enemyType;
	
	/**
	 * Constructor, will set the enemy's health to 100 and set its enemy type.
	 * @param enemyClassification is a String indicating the type of enemy it is
	 */
	public Enemy(String enemyClassification) {
		health = 100;
		enemyType = enemyClassification;
	}
	
	/**
	 * Getter, returns health, as required by Agent
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Will decrease the enemy's health by a value specified by the user - as required by Agent
	 */
	public void decreaseHealthBy(int value) {
		health = health - value;
		
	}

	/**
	 * Will increase the enemy's health by a value specified by the user - as required by Agent
	 */
	public void increaseHealthBy(int value) {
		health = health + value;	
	}
	
	/**
	 * Getter, will
	 * @return the enemy type
	 */
	public String getEnemyType() {
		return enemyType;
	}

}
