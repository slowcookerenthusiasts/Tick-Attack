import java.awt.Component;
import java.util.ArrayList;

public interface IGameView {
	
	/**
	 * A game view should be able to remove a component from the view.
	 * @param c is a component that can be removed from the view.
	 */
	public void removeFromView(Component c);
	
	/**
	 * A game view should be able to display the player's health
	 * @param value is the player's health
	 */
	public void showHealth(int value);
	
	/**
	 * A game view should be able to display the player's street cred
	 * @param value is the player's street cred
	 */
	public void showStreetCred(int value);
	
	/**
	 * A game view should be able to show the player's inventory
	 * @param list is an ArrayList of Item objects
	 */
	public void showInventory(ArrayList<Item> list);

}
