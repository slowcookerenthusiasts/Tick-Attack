
public class Item {
	
	private String name;
	private int healthBenefits;
	
	public Item(String itemName, int healthBonus) {
		name = itemName;
		healthBenefits = healthBonus;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHealthBenefits() {
		return healthBenefits;
	}

}
