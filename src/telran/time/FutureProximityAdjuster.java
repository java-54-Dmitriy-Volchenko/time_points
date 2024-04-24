package telran.time;

import telran.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster{
TimePoint[] timePoints; 

public FutureProximityAdjuster(TimePoint[] points) {
	 this.timePoints = Arrays.copy(points);
	 Arrays.bubbleSort(this.timePoints);	
	
}
	@Override
	public TimePoint adjust(TimePoint point) {
		//TODO
		return null;
	}

}