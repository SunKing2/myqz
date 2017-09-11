package myqz;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

public class QzFSTest5Unseen {

	private QzFS qfs = new QzFS("");
	int iAlgorithm = 0;

	String[] answers = {"QAT", "QIS"};
	int[] ratings = {100, 100};
	int[] ages = {0, 0};

	@Ignore
	@Test
	public void testTwoAnswersUnseenHalfRight() {
		String sExpected = 
		  "[1] AQT: qat\n" + 
		  "Correct.  (never:new-1)\n" + 
		  "[2] IQS: \n" + 
		  "The correct answer is 'QIS'  (new-100)\n" + 
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
		  "Mean solution time: 1.0 s\n" + 
		  "Mean difficulty: 50.5 s\n" + 
		  "Mean solution age: 8707 d\n" + 
		  "Oldest solution: never\n" + 
		  "";
		
		int iStartTime = 1504629289;
		int iEndTime = 1504629293;
		int[] absoluteResponseTimes = {1504629291, 1504629293};
		String[] responses = {"qat", ""};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, absoluteResponseTimes));
	}	
}
