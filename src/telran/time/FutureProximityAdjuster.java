package telran.time;

import telran.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster{
TimePoint[] timePoints; 

public FutureProximityAdjuster(TimePoint[] points) {
	 this.timePoints = Arrays.copy(points);
	 Arrays.bubbleSort(this.timePoints, (a,b)->a.compareTo(b));	
	
}


@Override
	public TimePoint adjust(TimePoint point) {
		int res = Arrays.binarySearch(timePoints, point, (a,b)->a.compareTo(b));
		
		return res>-1?timePoints[res+1]:timePoints[-res-1];
	}

}