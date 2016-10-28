import java.awt.Component;
import java.util.ArrayList;


public interface IGameView {
	
	public void removeFromView(Component c);
	
	public void showHealth(int value);
	
	public void showStreetCred(int value);
	
	public void showInventory(ArrayList<Item> list);

}
