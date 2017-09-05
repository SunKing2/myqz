package myqz;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class QzFSTest4ThreeQuestions {
	
	private QzFS qfs = new QzFS("");
	int iAlgorithm = 0;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testThreeLinesOfNoCorrect() {
		String sExpected = 
		  "[1] IIKP: \n" + 
		  "The correct answer is 'PIKI'  (100-100)\n" + 
		  "[2] AJNTU: \n" + 
		  "The correct answer is 'JAUNT JUNTA'  (100-100)\n" + 
		  "[3] ADIQ: \n" + 
		  "The correct answer is 'QADI QAID'  (31-100)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 0 questions correctly of 2 (0.0%).\n" + 
		  "Elapsed time: 0:00:06\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 3\n" + 
		  "Solved: 0 (0%)\n" + 
		  "Unsolved: 3 (100%)\n" + 
		  "Mean difficulty: 100.0 s\n" + 
		  "Mean solution age: 11 d\n" + 
		  "Oldest solution: 14 d\n" + 
		  "";

		double[] netResponseTimes = {2.88888888888889, 1.88888888888889, 0.88888888888889};
		
		String[] answers = {"QADI QAID", "PIKI", "JAUNT JUNTA"};
		int[] ratings = {31, 100, 100};
		int[] ages = {1503314531, 1503606435, 1503748624};
		int iStartTime = 1504586841;
		int iEndTime = 1504586847;
		int[] absoluteResponseTimes = {1504586844, 1504586846, 1504586847};
		
		String[] responses = {
				"",
				"",
				""
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
	
	@Test
	public void testThreeLinesOfAllCorrect() {
		String sExpected = 
		  "[1] IIKP: piki\n" + 
		  "Correct.  (11 d:100-69)\n" + 
		  "[2] AJNTU: jaunt junta\n" + 
		  "Correct.  (9 d:100-67)\n" + 
		  "[3] ADIQ: qadi qaid\n" + 
		  "Correct.  (14 d:31-22)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 3 questions correctly of 2 (150.0%).\n" + 
		  "You took on average 4.7 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:18\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 3\n" + 
		  "Solved: 3 (100%)\n" + 
		  "Unsolved: 0 (0%)\n" + 
		  "Mean solution time: 52.7 s\n" + 
		  "Mean solution age: 6 s\n" + 
		  "Oldest solution: 11 s\n" + 
		  "";

		double[] netResponseTimes = {6.44444444444444, 2.66666666666667, 4.88888888888889};
		
		String[] answers = {"QADI QAID", "PIKI", "JAUNT JUNTA"};
		int[] ratings = {31, 100, 100};
		int[] ages = {1503314531, 1503606435, 1503748624};
		int iStartTime = 1504591769;
		int iEndTime = 1504591787;
		int[] absoluteResponseTimes = {1504591776, 1504591780, 1504591786};
		
		String[] responses = {
				"piki",
				"jaunt junta",
				"qadi qaid"
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}

	@Test
	public void testThreeLinesOfLast2Correct() {
		String sExpected = 
		  "[1] IIKP: \n" + 
		  "The correct answer is 'PIKI'  (100-100)\n" + 
		  "[2] AJNTU: jaunt junta\n" + 
		  "Correct.  (9 d:100-68)\n" + 
		  "[3] ADIQ: qadi qaid\n" + 
		  "Correct.  (14 d:31-21)\n" + 
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered 2 questions correctly of 2 (100.0%).\n" + 
		  "You took on average 3.8 seconds to answer correctly.\n" + 
		  "Congratulations!\n" + 
		  "Elapsed time: 0:00:11\n" + 
		  "\n" + 
		  "Current statistics for this question set:\n" + 
		  "Total: 3\n" + 
		  "Solved: 2 (67%)\n" + 
		  "Unsolved: 1 (33%)\n" + 
		  "Mean solution time: 44.5 s\n" + 
		  "Mean difficulty: 63.0 s\n" + 
		  "Mean solution age: 3 d\n" + 
		  "Oldest solution: 11 d\n" + 
		  "";

		double[] netResponseTimes = {0.888888888888889, 4.66666666666667, 2.88888888888889};
		
		String[] answers = {"QADI QAID", "PIKI", "JAUNT JUNTA"};
		int[] ratings = {31, 100, 100};
		int[] ages = {1503314531, 1503606435, 1503748624};
		int iStartTime = 1504592071;
		int iEndTime = 1504592082;
		int[] absoluteResponseTimes = {1504592072, 1504592078, 1504592082};
		
		String[] responses = {
				"",
				"jaunt junta",
				"qadi qaid"
		};
		
		assertEquals(sExpected, qfs.process(iStartTime, iEndTime, iAlgorithm, answers, ratings, ages, responses, netResponseTimes, absoluteResponseTimes));
	}
}
