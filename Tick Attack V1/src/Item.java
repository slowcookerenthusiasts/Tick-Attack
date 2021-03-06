public class Item {
	
	private String name;
	private int healthBenefits;
	
	/**
	 * Constructor, will take in a name and a health bonus
	 * @param itemName is a String, specifying the name of the item
	 * @param healthBonus is an integer, specifying the health benefit the item
	 * grants the player.
	 */
	public Item(String itemName, int healthBonus) {
		name = itemName;
		healthBenefits = healthBonus;
	}
	
	/**
	 * Getter, will
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter, will
	 * @return the health benefits the item provides.
	 */
	public int getHealthBenefits() {
		return healthBenefits;
	}

}
