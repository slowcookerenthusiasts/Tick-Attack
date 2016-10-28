import java.util.TimerTask;
public class StreetCredTimeTask extends TimerTask {
	
	private int count = 0;
	
	public void run() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
}
