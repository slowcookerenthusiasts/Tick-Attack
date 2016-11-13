
public class CountCondition implements Condition{

	int counter;
	int upTo;
	
	public CountCondition(int upTo){
		this.counter = 0;
		this.upTo = upTo;
	}
	
	@Override
	public boolean met() {
		if (counter >= upTo)
			return true;
		increment();
		return false;
	}
	
	private void increment(){
		counter++;
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null)
			return false;
		if (!o.getClass().isAssignableFrom(CountCondition.class))
			return false;
		
		CountCondition c = (CountCondition) o;
		
		return (c.upTo == this.upTo);
	}

}