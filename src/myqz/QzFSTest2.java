package myqz;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("unused")
public class QzFSTest2 {
	
	private QzFS qfs = new QzFS("");
	int iAlgorithm = 0;

	String[] answers = {"QIS", "QAT"};
	int[] ratings = {100, 100};
	int[] ages = {0, 0};

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

		int iStartTime = 1504582250;
		int iEndTime = 1504582268;
		int[] absoluteResponseTimes = {1504582258, 1504582268};
		String[] responses = {"", ""};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
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

		int iStartTime = 1504582464;
		int iEndTime = 1504582468;
		int[] absoluteResponseTimes = {1504582466, 1504582468};
		String[] responses = {"qis", "qat"};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
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
		
		int iStartTime = 1504583319;
		int iEndTime = 1504583329;
		int[] absoluteResponseTimes = {1504583322, 1504583329};
		String[] responses = {"qis", ""};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
	}
	
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

		int iStartTime = 1504583454;
		int iEndTime = 1504583458;
		int[] absoluteResponseTimes = {1504583456, 1504583458};
		String[] responses = {"", "qat"};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
	}
	
}
