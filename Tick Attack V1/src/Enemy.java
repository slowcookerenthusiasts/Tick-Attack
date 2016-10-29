public class Enemy implements Agent{
	
	private int health;
	private String enemyType;
	
	public Enemy(String enemyClassification) {
		health = 100;
		enemyType = enemyClassification;
	}
	
	public int getHealth() {
		return health;
	}

	public void decreaseHealthBy(int value) {
		health = health - value;
		
	}

	public void increaseHealthBy(int value) {
		health = health + value;	
	}
	
	public String getEnemyType() {
		return enemyType;
	}

}
