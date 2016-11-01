public class Enemy extends Agent{
	
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
	 * Getter, will
	 * @return the enemy type
	 */
	public String getEnemyType() {
		return enemyType;
	}

}
