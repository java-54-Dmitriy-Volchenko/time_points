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
		//TODO
	}
	@Test
	void futureProximityAdjusterTest() {
		//TODO
	}

}