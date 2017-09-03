package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QzFSTest2 {
	
	private String sFileData = 
	  "AQT	QAT	100	0	CO	\n" + 
	  "IQS	QIS	100	0	CO	\n" + 
	  "";

	private QzFS qfs = new QzFS(sFileData);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTwoAnswersHalfRight() {
		String sExpected = 
		  "[1] AQT: qat\n" + 
		  "Correct.  (never:100-68)\n" + 
		  "[2] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 4.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:08\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 68.0 s\n" + 
		  "Mean difficulty: 84.0 s\n" + 
		  "Mean solution age: 8706 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] responseTimes = {4.55555555555556, 2.55555555555556};

		String[] answers = {"QAT", "QIS"};

		String[] responses = {
				"qat",
				"",
		};
		
		assertEquals(sExpected, qfs.process(answers, responses, responseTimes));
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
		  "You took on average 0.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:01\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean difficulty: 83.5 s\n" + 
		  "Mean solution age: 8706 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] responseTimes = {0.555555555555556, -0.111111111111111};

		String[] answers = {"QIS", "QAT"};

		String[] responses = {
				"qis",
				"",
		};
		
		assertEquals(sExpected, qfs.process(answers, responses, responseTimes));
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
		  "Elapsed time: 0:00:06\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 67.0 s\n" + 
		  "Mean difficulty: 83.5 s\n" + 
		  "Mean solution age: 8706 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] responseTimes = {4.88888888888889, 0.555555555555556};

		String[] answers = {"QAT", "QIS"};

		String[] responses = {
				"",
				"qis",
		};
		
		assertEquals(sExpected, qfs.process(answers, responses, responseTimes));
	}

	// this test also passed first time, so maybe it's not needed
	@Test
	public void testReversedTwoAnswersSecondHalfRight() {
		String sExpected = 
		  "[1] IQS: \n" + 
		  "The correct answer is 'QIS'  (100-100)\n" + 
		  "[2] AQT: qat\n" + 
		  "Correct.  (never:100-72)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 1 question correctly of 1 (100.0%).\n" + 
		  "You took on average 16.6 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:21\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 2\n" + 
		  "Solved: 1 (50%)\n" + 
		  "Unsolved: 1 (50%)\n" + 
		  "Mean solution time: 72.0 s\n" + 
		  "Mean difficulty: 86.0 s\n" + 
		  "Mean solution age: 8706 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		double[] responseTimes = {3.88888888888889, 16.5555555555556};

		String[] answers = {"QIS", "QAT"};

		String[] responses = {
				"",
				"qat",
		};
		
		assertEquals(sExpected, qfs.process(answers, responses, responseTimes));
	}
	
}
