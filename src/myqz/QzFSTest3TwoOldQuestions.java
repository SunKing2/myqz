package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("unused")
public class QzFSTest3TwoOldQuestions {
	
	private QzFS qfs = new QzFS("");
	int iAlgorithm = 0;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTwoOfTwoBlankAnswersOldQuestions() {
		String sExpected = 
		  "[1] IQS: \n" + 
		  "The correct answer is 'QIS'  (70-100)\n" + 
		  "[2] AQT: \n" + 
		  "The correct answer is 'QAT'  (67-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 0 questions correctly of 1 (0.0%).\n" + 
		  "Elapsed time: 0:00:06\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 2 h\n" + 
		  "Oldest solution: 2 h\n" + 
		  "";

		double[] responseTimes = {2.88888888888889, 2.88888888888889};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {67, 70};
		int[] ages = {1504471490, 1504471501};
		int iStartTime = 1504480797;
		int iEndTime = 1504480803;
		
		String[] responses = {
				"",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
	
	@Test
	public void testTwoOfTwoCorrectAnswersOldQuestions() {
		String sExpected = 
		  "[1] IQS: qis\n" + 
		  "Correct.  (4 h:70-47)\n" + 
		  "[2] AQT: qat\n" + 
		  "Correct.  (4 h:67-46)\n" + 
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
		  "Mean solution time: 46.5 s\n" + 
		  "Mean solution age: 2 s\n" + 
		  "Oldest solution: 4 s\n" + 
		  "";

		double[] responseTimes = {2.55555555555556, 3.55555555555556};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {67, 70};
		int[] ages = {1504471490, 1504471501};
		int iStartTime = 1504485980;
		int iEndTime = 1504485987;
		
		String[] responses = {
				"qis",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, responseTimes));
	}
	
}
