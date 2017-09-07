package myqz;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("unused")
public class QzFSTest1 {
	
	private QzFS qfs = new QzFS("");
	int iAlgorithm = 0;

	String[] answers = {"QAT", "QIS"};
	int[] ratings = {100, 100};
	int[] ages = {0, 0};

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

		int iStartTime = 1504577540;
		int iEndTime = 1504577549;
		int[] absoluteResponseTimes = {1504577543, 1504577549};
		String[] responses = {"", ""};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
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

		int iStartTime = 1504578195;
		int iEndTime = 1504578202;
		int[] absoluteResponseTimes = {1504578197, 1504578202};
		String[] responses = {"qat", "qis"};

		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
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
		
		int iStartTime = 1504582651;
		int iEndTime = 1504582661;
		int[] absoluteResponseTimes = {1504582658, 1504582661};
		String[] responses = {"qat", ""};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
	}
	
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
		
		int iStartTime = 1504583104;
		int iEndTime = 1504583112;
		int[] absoluteResponseTimes = {1504583111, 1504583112};
		String[] responses = {"", "qis"};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
	}
}
