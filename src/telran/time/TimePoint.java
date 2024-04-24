package telran.time;

import java.util.Objects;

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
		int seconds = this.amount * this.timeUnit.getValue();
		int actualAmount = seconds/unit.getValue();
		return this.timeUnit.equals(unit)?this:new TimePoint(actualAmount, unit);
		
	}
	public TimePoint with(TimePointAdjuster adjuster) {
		
		return adjuster.adjust(this);
	}
	
	@Override
	public int compareTo(TimePoint o) {
		int res = Integer.compare(this.convert(TimeUnit.SECOND).getAmount(), o.convert(TimeUnit.SECOND).getAmount());
	    return res;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, timeUnit);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		 TimePoint other = (TimePoint) obj;
		 TimePoint thisSec = this.convert(TimeUnit.SECOND);
		 TimePoint otherSec = other.convert(TimeUnit.SECOND);		
		
		return thisSec.getAmount()==otherSec.getAmount();
	}
	
	
}