package telran.time;

public class TimePoint implements Comparable<TimePoint>{
	int amount;
	TimeUnit timeUnit;
	public TimePoint(int amount, TimeUnit timeUnit) {
		this.amount = amount;
		this.timeUnit = timeUnit;
	}
	public int getAmount() {
		return amount;
	}
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public TimePoint convert(TimeUnit unit) {
		// TODO Auto-generated method stub
		//returns new TimePoint with a given TimeUnit
		return null;
	}
	public TimePoint with(TimePointAdjuster adjuster) {
		//TODO
		//returns new TimePoint based on any TimePointAdjuster
		return null;
	}
	@Override
	public int compareTo(TimePoint o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		//TODO
		return false;
	}
	
	
}