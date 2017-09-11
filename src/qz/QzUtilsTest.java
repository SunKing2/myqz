package qz;

import static org.junit.Assert.*;

import org.junit.Test;

public class QzUtilsTest {

	@Test
	public void testSecondsToHumanAroundOneHour() {
		assertEquals("1 h", QzUtils.secondsToHuman(3600));
		assertEquals("1 h", QzUtils.secondsToHuman(3601));
		assertEquals("1 h", QzUtils.secondsToHuman(3660));
		assertEquals("1 h", QzUtils.secondsToHuman(3661));
		assertEquals("59 m", QzUtils.secondsToHuman(3559));
	}
	
	@Test
	public void testSecondsToHumanAroundOneMinute() {
		assertEquals("1 m", QzUtils.secondsToHuman(60));
		assertEquals("1 m", QzUtils.secondsToHuman(61));
		assertEquals("59 s", QzUtils.secondsToHuman(59));
	}

}
