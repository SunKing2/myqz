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
		  "[1] AQT: qat\n" + 
		  "Correct.  (never:100-69)\n" + 
		  "[2] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 6.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:10\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 69.0 s\n" + 
		  "Mean difficulty: 84.5 s\n" + 
		  "Mean solution age: 8707 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] netResponseTimes = {6.55555555555556, 2.88888888888889};

		String[] answers = {"QAT", "QIS"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504582651;
		int iEndTime = 1504582661;
		int[] absoluteResponseTimes = {1504582658, 1504582661};
		
		String[] responses = {
				"qat",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	// this test passed first time, so maybe it's not needed
	@Test
	public void testTwoAnswersSecondHalfRight() {
		String sExpected = 
		  "[1] AQT: \n" + 
		  "The correct answer is 'QAT'  (100-100)\n" + 
		  "[2] IQS: qis\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 0.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:08\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean difficulty: 83.5 s\n" + 
		  "Mean solution age: 8707 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] netResponseTimes = {6.88888888888889, 0.555555555555556};

		String[] answers = {"QAT", "QIS"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504583104;
		int iEndTime = 1504583112;
		int[] absoluteResponseTimes = {1504583111, 1504583112};
		
		String[] responses = {
				"",
				"qis",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}

	@Test
	public void testReversedTwoAnswersHalfRight() {
		String sExpected = 
		  "[1] IQS: qis\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "[2] AQT: \n" + 
		  "The correct answer is 'QAT'  (100-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 2.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:10\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean difficulty: 83.5 s\n" + 
		  "Mean solution age: 8707 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] netResponseTimes = {2.555555555555556, 6.88888888888889};

		String[] answers = {"QIS", "QAT"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504583319;
		int iEndTime = 1504583329;
		int[] absoluteResponseTimes = {1504583322, 1504583329};
		
		String[] responses = {
				"qis",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	// this test also passed first time, so maybe it's not needed
	@Test
	public void testReversedTwoAnswersSecondHalfRight() {
		String sExpected = 
		  "[1] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "[2] AQT: qat\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 1.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:04\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean difficulty: 83.5 s\n" + 
		  "Mean solution age: 8707 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] netResponseTimes = {1.88888888888889, 1.5555555555556};

		String[] answers = {"QIS", "QAT"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504583454;
		int iEndTime = 1504583458;
		int[] absoluteResponseTimes = {1504583456, 1504583458};
		
		String[] responses = {
				"",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
}
