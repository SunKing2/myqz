package myqz;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyQzTest {
	MyQz qz = new MyQz();

	@Test
	public void testScore1() {
		testOriginalToExpected(true, 100, 67);
		testOriginalToExpected(true, 99, 67);
		testOriginalToExpected(true, 50, 34);
		testOriginalToExpected(true, 10, 7);
		testOriginalToExpected(true, 2, 2);
		testOriginalToExpected(true, 1, 1);
	}
	@Test
	public void testScore2() {
		testOriginalToExpected(false, 67, 100);
		testOriginalToExpected(false, 34, 100);
		testOriginalToExpected(false, 34, 100);
		testOriginalToExpected(false, 7, 100);
		testOriginalToExpected(false, 2, 100);
		testOriginalToExpected(false, 1, 100);
	}
	private void testOriginalToExpected(boolean correct, int originalScore, int expected) {
		int actual = qz.score(correct, originalScore);
		assertEquals(expected, actual);
	}
}
