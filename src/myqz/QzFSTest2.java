package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


@SuppressWarnings("unused")
public class QzFSTest2 {
	
	private QzFS qfs = new QzFS("");
	int[] ratings = {100, 100};
	int[] ages = {0, 0};
	int iStartTime = 0;
	int iEndTime = 0;
	int iAlgorithm = 0;


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTwoAnswersHalfRight() {
		String sExpected = 
		  "";
		
		double[] responseTimes = {4.55555555555556, 2.55555555555556};

		String[] answers = {"QAT", "QIS"};

		String[] responses = {
				"qat",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
	
	@Test
	public void testReversedTwoAnswersHalfRight() {
		String sExpected = 
		  "";
		
		double[] responseTimes = {0.555555555555556, -0.111111111111111};

		String[] answers = {"QIS", "QAT"};

		String[] responses = {
				"qis",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
	
	// this test passed first time, so maybe it's not needed
	@Test
	public void testTwoAnswersSecondHalfRight() {
		String sExpected = 
		  "";
		
		double[] responseTimes = {4.88888888888889, 0.555555555555556};

		String[] answers = {"QAT", "QIS"};

		String[] responses = {
				"",
				"qis",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}

	// this test also passed first time, so maybe it's not needed
	@Test
	public void testReversedTwoAnswersSecondHalfRight() {
		String sExpected = 
		  "";
		
		double[] responseTimes = {3.88888888888889, 16.5555555555556};

		String[] answers = {"QIS", "QAT"};

		String[] responses = {
				"",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
	
}
