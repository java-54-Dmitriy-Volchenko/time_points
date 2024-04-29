package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import telran.time.*;

class TimePointTest {

	@Test
	void testBetween() {
		TimePoint point1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint point2 = new TimePoint(3600 * 20, TimeUnit.SECOND);
		TimePoint point3 = TimeUnit.MINUTE.between(point1, point2);
		assertEquals(600, point3.getAmount());
		TimePoint point4 = TimeUnit.SECOND.between(point1, point2);
		assertEquals(36000, point4.getAmount());
		TimePoint point5 = TimeUnit.HOUR.between(point1, point2);
		assertEquals(10, point5.getAmount());
	
	}
	@Test
	void convertTest() {
		TimePoint timePoint = new TimePoint(10, TimeUnit.HOUR);
		TimePoint point1Actual = timePoint.convert(TimeUnit.SECOND);
		assertEquals(36000, point1Actual.getAmount());
	}
	@Test
	void plusAdjusterTest() {
		TimePoint timePoint1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint timePoint2 = new TimePoint(60, TimeUnit.MINUTE);
		TimePoint actual = timePoint2.with(new PlusAdjuster(timePoint1));
		assertEquals(660, actual.getAmount());
		assertEquals(TimeUnit.MINUTE, actual.getTimeUnit());
	}
	@Test
	void timePointEqualsTest() {
		TimePoint first = new TimePoint (1, TimeUnit.HOUR);	
		TimePoint second = new TimePoint (1, TimeUnit.HOUR);
		TimePoint third = new TimePoint (1, TimeUnit.MINUTE);	
		TimePoint fourth = new TimePoint (60, TimeUnit.MINUTE);
		TimePoint fifth = new TimePoint (3600, TimeUnit.SECOND);
		assertTrue(first.equals(first));
		assertFalse(first.equals(null));		
		assertTrue(first.equals(second));
		assertFalse(first.equals(third));
		assertTrue(first.equals(fourth));
		assertTrue(first.equals(fifth));
		
		
	}
	@Test
	void timePointCompareToTest() {
		TimePoint first = new TimePoint (1, TimeUnit.HOUR);	
		TimePoint second = new TimePoint (1, TimeUnit.HOUR);
		TimePoint third = new TimePoint (1, TimeUnit.MINUTE);	
		TimePoint fourth = new TimePoint (60, TimeUnit.MINUTE);
		TimePoint fifth = new TimePoint (3600, TimeUnit.SECOND);
		assertTrue(first.compareTo(second)==0);
		assertTrue(first.compareTo(fourth)==0);
		assertTrue(first.compareTo(third)>0);
		assertTrue(fifth.compareTo(third)>0);
		assertTrue(third.compareTo(fifth)<0);
	}
	@Test
	void futureProximityAdjusterTest() {
		TimePoint zeroIdx = new TimePoint(2, TimeUnit.HOUR);//6
		TimePoint oneIdx = new TimePoint(5, TimeUnit.MINUTE);//4
		TimePoint twoIdx = new TimePoint(15, TimeUnit.SECOND);//1
		TimePoint threeIdx = new TimePoint(1, TimeUnit.MINUTE);//3.2
		TimePoint foreIdx = new TimePoint(3, TimeUnit.HOUR);//7
		TimePoint fiveIdx = new TimePoint(25, TimeUnit.MINUTE);//5
		TimePoint sixIdx = new TimePoint(0, TimeUnit.MINUTE);//0
		TimePoint sevenIdx = new TimePoint(12, TimeUnit.HOUR);//8
		TimePoint eightIdx = new TimePoint(30, TimeUnit.SECOND);//2.1
		TimePoint nineIdx = new TimePoint(30, TimeUnit.SECOND);//2.2
		TimePoint tenIdx = new TimePoint(60, TimeUnit.SECOND);//3.1
		
		TimePoint[]points = {zeroIdx,oneIdx,twoIdx,threeIdx, foreIdx, fiveIdx, sixIdx, sevenIdx, eightIdx, nineIdx, tenIdx};
		TimePoint point1 = new TimePoint(0, TimeUnit.MINUTE);		
		TimePoint actual1 = point1.with(new FutureProximityAdjuster(points));		
		assertEquals(15, actual1.getAmount());
		TimePoint point2 = new TimePoint(3, TimeUnit.HOUR);		
		TimePoint actual2 = point2.with(new FutureProximityAdjuster(points));		
		assertEquals(12, actual2.getAmount());
		TimePoint point3 = new TimePoint(26, TimeUnit.MINUTE);		
		TimePoint actual3 = point3.with(new FutureProximityAdjuster(points));		
		assertEquals(2, actual3.getAmount());
		TimePoint point4 = new TimePoint(31, TimeUnit.SECOND);		
		TimePoint actual4 = point4.with(new FutureProximityAdjuster(points));		
		assertEquals(1, actual4.getAmount());
		
		TimePoint point5 = new TimePoint(721, TimeUnit.MINUTE);	
		TimePoint actual5 = point5.with(new FutureProximityAdjuster(points));
	    assertTrue(actual5==null);
		
		TimePoint point6 = new TimePoint(30, TimeUnit.SECOND);		
		TimePoint actual6 = point6.with(new FutureProximityAdjuster(points));		
		assertEquals(1, actual6.getAmount());
		
		TimePoint point7 = new TimePoint(60, TimeUnit.SECOND);		
		TimePoint actual7 = point7.with(new FutureProximityAdjuster(points));		
		assertEquals(5, actual7.getAmount());
		
		TimePoint zeroIdxs = new TimePoint(20, TimeUnit.HOUR);//3
		TimePoint oneIdxs = new TimePoint(14, TimeUnit.HOUR);//2.2
		TimePoint twoIdxs = new TimePoint(14, TimeUnit.HOUR);//2.1
		TimePoint threeIdxs = new TimePoint(13, TimeUnit.HOUR);//1
		TimePoint foreIdxs = new TimePoint(12, TimeUnit.HOUR);//0
		
		//teacjer's test
		TimePoint[]points1 = {zeroIdxs,oneIdxs,twoIdxs,threeIdxs, foreIdxs};
		//this one works without any corrections
		TimePoint point11 = new TimePoint(14, TimeUnit.HOUR);		
		TimePoint actual11 = point11.with(new FutureProximityAdjuster(points1));		
		assertEquals(20, actual11.getAmount());		
		//this one works after correction
		TimePoint point12 = new TimePoint(20, TimeUnit.HOUR);		
		TimePoint actual12 = point12.with(new FutureProximityAdjuster(points1));		
		assertTrue(actual12==null);
		
		TimePoint zeroIdxss = new TimePoint(20, TimeUnit.HOUR);//2		
		TimePoint oneIdxss = new TimePoint(14, TimeUnit.HOUR);//1
		TimePoint twoIdxss = new TimePoint(13, TimeUnit.HOUR);//0
		
		
		
		TimePoint[]points2 = {zeroIdxss,oneIdxss,twoIdxss};
		
		TimePoint point13 = new TimePoint(15, TimeUnit.HOUR);		
		TimePoint actual13 = point13.with(new FutureProximityAdjuster(points2));		
		assertEquals(20, actual13.getAmount());		
		
		
		TimePoint zeroIdxssa = new TimePoint(20, TimeUnit.HOUR);//1		
		TimePoint oneIdxssa = new TimePoint(14, TimeUnit.HOUR);//0
		
		
		TimePoint[]points3 = {zeroIdxssa,oneIdxssa};
		
		TimePoint point14 = new TimePoint(15, TimeUnit.HOUR);		
		TimePoint actual14 = point14.with(new FutureProximityAdjuster(points3));		
		assertEquals(20, actual14.getAmount());	
		
		TimePoint point15 = new TimePoint(20, TimeUnit.HOUR);		
		TimePoint actual15 = point15.with(new FutureProximityAdjuster(points3));		
		assertTrue(actual15==null);
		
		TimePoint zeroIdxssab = new TimePoint(20, TimeUnit.HOUR);//0	
		
		TimePoint[]points4 = {zeroIdxssab};
		
		TimePoint point16 = new TimePoint(15, TimeUnit.HOUR);		
		TimePoint actual16 = point16.with(new FutureProximityAdjuster(points4));		
		assertEquals(20, actual16.getAmount());	
		
		TimePoint point17 = new TimePoint(20, TimeUnit.HOUR);		
		TimePoint actual17 = point17.with(new FutureProximityAdjuster(points3));		
		assertTrue(actual17==null);
		
		TimePoint[]points5 = {};
		
		TimePoint point18 = new TimePoint(20, TimeUnit.HOUR);		
		TimePoint actual18 = point18.with(new FutureProximityAdjuster(points5));		
		assertTrue(actual18==null);
		
	    }	
	
	 	
	 	
    }	
	

