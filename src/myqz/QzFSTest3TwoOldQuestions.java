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
		  "Elapsed time: 0:00:10\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 2 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 1 d\n" + 
		  "Oldest solution: 1 d\n" + 
		  "";

		double[] netResponseTimes = {8.88888888888889, 0.88888888888889};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {67, 70};
		int[] ages = {1504471490, 1504471501};
		int iStartTime = 1504583682;
		int iEndTime = 1504583692;
		int[] absoluteResponseTimes = {1504583691, 1504583692};
		
		String[] responses = {
				"",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	@Test
	public void testTwoOfTwoCorrectAnswersOldQuestions() {
		String sExpected = 
		  "[1] IQS: qis\n" + 
		  "Correct.  (1 d:70-49)\n" + 
		  "[2] AQT: qat\n" + 
		  "Correct.  (1 d:67-47)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 2 questions correctly of 1 (200.0%).\n" + 
		  "You took on average 7.1 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:15\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 2 (100%)\n" + 
		  "Unsolved: 0 (0%)\n" + 
		  "Mean solution time: 48.0 s\n" + 
		  "Mean solution age: 4 s\n" + 
		  "Oldest solution: 8 s\n" + 
		  "";

		double[] netResponseTimes = {6.55555555555556, 7.55555555555556};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {67, 70};
		int[] ages = {1504471490, 1504471501};
		int iStartTime = 1504583880;
		int iEndTime = 1504583895;
		int[] absoluteResponseTimes = {1504583887, 1504583895};
		
		String[] responses = {
				"qis",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	@Test
	public void testTwoOfFirstCorrectAnswerOldQuestions() {
		String sExpected = 
		  "[1] IQS: qis\n" + 
		  "Correct.  (1 d:70-47)\n" + 
		  "[2] AQT: \n" + 
		  "The correct answer is 'QAT'  (67-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 2.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:05\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 47.0 s\n" + 
		  "Mean difficulty: 73.5 s\n" + 
		  "Mean solution age: 15 h\n" + 
		  "Oldest solution: 1 d\n" + 
		  "";

		double[] netResponseTimes = {2.55555555555556, 1.88888888888889};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {67, 70};
		int[] ages = {1504471490, 1504471501};
		int iStartTime = 1504584946;
		int iEndTime = 1504584951;
		int[] absoluteResponseTimes = {1504584949, 1504584951};
		
		String[] responses = {
				"qis",
				"",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	@Test
	public void testTwoOfSecondCorrectAnswerOldQuestions() {
		String sExpected = 
		  "[1] IQS: \n" + 
		  "The correct answer is 'QIS'  (70-100)\n" + 
		  "[2] AQT: qat\n" + 
		  "Correct.  (1 d:67-45)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 0.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:03\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 45.0 s\n" + 
		  "Mean difficulty: 72.5 s\n" + 
		  "Mean solution age: 15 h\n" + 
		  "Oldest solution: 1 d\n" + 
		  "";

		double[] netResponseTimes = {1.88888888888889, 0.555555555555556};
		
		String[] answers = {"QAT", "QIS"};
		int[] ratings = {67, 70};
		int[] ages = {1504471490, 1504471501};
		int iStartTime = 1504585134;
		int iEndTime = 1504585137;
		int[] absoluteResponseTimes = {1504585136, 1504585137};
		
		String[] responses = {
				"",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
}
