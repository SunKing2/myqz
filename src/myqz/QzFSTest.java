package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QzFSTest {
	
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
	public void testTwoOfTwoBlankAnswers() {
		String sExpected = 
		  "[1] AQT: \n" + 
		  "The correct answer is 'QAT'  (100-100)\n" + 
		  "[2] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 0 questions correctly of 1 (0.0%).\n" + 
		  "Elapsed time: 0:00:02\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 17412 d\n" + 
		  "Oldest solution: never\n" + 
		  "";

		double[] responseTimes = {0.5, 0.5};
		
		String[] answers = {"QAT", "QIS"};
		
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
		  "Mean solution age: 1 s\n" + 
		  "Oldest solution: 2 s\n" + 
		  "";
		
		double[] responseTimes = {1.55555555555556, 1.55555555555556};

		String[] answers = {"QAT", "QIS"};

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
		  "Elapsed time: 0:00:05\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 17412 d\n" + 
		  "Oldest solution: never\n" + 
		  "";

		double[] responseTimes = {2.0, 2.0};

		String[] answers = {"QIS", "QAT"};
		
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
		  "You took on average 2.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:06\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 2 (100%)\n" + 
		  "Unsolved: 0 (0%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean solution age: 1 s\n" + 
		  "Oldest solution: 3 s\n" + 
		  "";
		
		double[] responseTimes = {2.55555555555556, 2.55555555555556};

		String[] answers = {"QIS", "QAT"};

		String[] responses = {
				"qis",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
}
