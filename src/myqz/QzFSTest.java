package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("unused")
public class QzFSTest {
	
	private QzFS qfs = new QzFS("");
	int iAlgorithm = 0;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTwoOfTwoBlankAnswers() {
		String sExpected = 
		  "[1] AQT: \n" + 
		  "The correct answer is 'QAT'  (100-100)\n" + 
		  "[2] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 0 questions correctly of 1 (0.0%).\n" + 
		  "Elapsed time: 0:00:09\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 17414 d\n" + 
		  "Oldest solution: never\n" + 
		  "";

		double[] netResponseTimes = {2.88888888888889, 5.88888888888889};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504577540;
		int iEndTime = 1504577549;
		int[] absoluteResponseTimes = {1504577543, 1504577549};
		
		String[] responses = {
				"",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	@Test
	public void testTwoOfTwoCorrectAnswers() {
		String sExpected = 
		  "[1] AQT: qat\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "[2] IQS: qis\n" + 
		  "Correct.  (never:100-68)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 2 questions correctly of 1 (200.0%).\n" + 
		  "You took on average 3.1 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:07\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 2 (100%)\n" + 
		  "Unsolved: 0 (0%)\n" + 
		  "Mean solution time: 67.5 s\n" + 
		  "Mean solution age: 2 s\n" + 
		  "Oldest solution: 5 s\n" + 
		  "";

		double[] netResponseTimes = {1.55555555555556, 4.555555555555556};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504578195;
		int iEndTime = 1504578202;
		int[] absoluteResponseTimes = {1504578197, 1504578202};
		
		String[] responses = {
				"qat",
				"qis",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	

	@Test
	public void testReverseTwoOfTwoBlankAnswers() {
		String sExpected = 
		  "[1] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "[2] AQT: \n" + 
		  "The correct answer is 'QAT'  (100-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 0 questions correctly of 1 (0.0%).\n" + 
		  "Elapsed time: 0:00:18\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 17414 d\n" + 
		  "Oldest solution: never\n" + 
		  "";

		double[] netResponseTimes = {7.88888888888889, 9.88888888888889};
		
		String[] answers = {"QIS", "QAT"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504582250;
		int iEndTime = 1504582268;
		int[] absoluteResponseTimes = {1504582258, 1504582268};
		
		String[] responses = {
				"",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}

	
	@Test
	public void testReversedTwoOfTwoCorrectAnswers() {
		String sExpected = 
		  "[1] IQS: qis\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "[2] AQT: qat\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 2 questions correctly of 1 (200.0%).\n" + 
		  "You took on average 1.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:04\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 2 (100%)\n" + 
		  "Unsolved: 0 (0%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean solution age: 1 s\n" + 
		  "Oldest solution: 2 s\n" + 
		  "";

		double[] netResponseTimes = {1.55555555555556, 1.555555555555556};
		
		String[] answers = {"QIS", "QAT"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504582464;
		int iEndTime = 1504582468;
		int[] absoluteResponseTimes = {1504582466, 1504582468};
		
		String[] responses = {
				"qis",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
}
