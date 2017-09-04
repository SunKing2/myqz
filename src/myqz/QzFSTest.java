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
		  "Elapsed time: 0:00:08\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 17413 d\n" + 
		  "Oldest solution: never\n" + 
		  "";

		double[] responseTimes = {2.88888888888889, 4.88888888888889};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504537176;
		int iEndTime = 1504537184;
		
		String[] responses = {
				"",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
	
	@Test
	public void testTwoOfTwoCorrectAnswers() {
		String sExpected = 
		  "[1] AQT: qat\n" + 
		  "Correct.  (never:100-67)\n" + 
		  "[2] IQS: qis\n" + 
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
		  "Mean solution age: 0 s\n" + 
		  "Oldest solution: 1 s\n" + 
		  "";

		double[] responseTimes = {2.55555555555556, 0.555555555555556};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504541061;
		int iEndTime = 1504541065;
		
		String[] responses = {
				"qat",
				"qis",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
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
		  "Elapsed time: 0:00:07\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 17413 d\n" + 
		  "Oldest solution: never\n" + 
		  "";

		double[] responseTimes = {3.88888888888889, 2.88888888888889};
		
		String[] answers = {"QIS", "QAT"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504548394;
		int iEndTime = 1504548401;
		
		String[] responses = {
				"",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
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
		  "Mean solution age: 0 s\n" + 
		  "Oldest solution: 1 s\n" + 
		  "";

		double[] responseTimes = {2.55555555555556, 0.555555555555556};
		
		String[] answers = {"QIS", "QAT"};
		int[] ratings = {100, 100};
		int[] ages = {0, 0};
		int iStartTime = 1504548920;
		int iEndTime = 1504548924;
		
		String[] responses = {
				"qis",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
}
