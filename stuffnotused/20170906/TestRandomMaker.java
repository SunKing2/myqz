package myqz;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TestRandomMaker {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRandomMaker() {
		HashMap<Integer, Double> map = RandomMaker.randomMaker(Arrays.asList(31, 100, 67, 100));
		
		String sActual = map.toString();
		
		String sExpected = "{0=0.125, 1=1.0, 2=0.5, 3=1.0}";
		assertEquals (sExpected, sActual);
	}
	@Test
	public void testRandomMaker2() {
		HashMap<Integer, Double> map = RandomMaker.randomMaker(Arrays.asList(200, 0, -12, 100, 1, 2));
		
		String sActual = map.toString();
		
		String sExpected = "{0=1.0, 1=5.0E-4, 2=5.0E-4, 3=1.0, 4=5.0E-4, 5=9.765625E-4}";
		assertEquals (sExpected, sActual);
	}
	

	@Test
	public void testIsChosen() {
		// expected, testNumber, iterations, marginOfError
		assertIsChosenManyTimes(1250, 44, 10000, 175);
		assertIsChosenManyTimes(1250, 31, 10000, 175);
		assertIsChosenManyTimes(1250, 30, 10000, 175);
		

		assertIsChosenManyTimes(1000, 100, 1000, 150);
		assertIsChosenManyTimes(500,   67, 1000,  75);
		assertIsChosenManyTimes(250,   46, 1000,  49); // 45
		assertIsChosenManyTimes(125,   44, 1000,  30); // 30
		
		assertIsChosenManyTimes(125,   44, 1000,  30); // 30
		assertIsChosenManyTimes(125,   31, 1000,  30); // 30
		assertIsChosenManyTimes(125,   30, 1000,  30); // 30
		
		assertIsChosenManyTimes( 63,   20, 1000,  22);
		assertIsChosenManyTimes( 31,   13, 1000,  15);
		assertIsChosenManyTimes( 16,    9, 1000,   8);
		
		assertIsChosenManyTimes(780,    6, 100000,   100);
		assertIsChosenManyTimes(390,    4, 100000,   50);
		assertIsChosenManyTimes(200,    3, 100000,   33);
		assertIsChosenManyTimes(100,    2, 100000,   26);
		assertIsChosenManyTimes( 50,    1, 100000,   21);
		 
	}
	@Test
	public void testMakeList() {
		// pretend we have a list which just happens to have 
		// rating as follows
		// item 1 rating 1
		// ..
		// item 100 rating 100;
		Set<Integer> myQuestions = new HashSet<Integer>();
		// make 20 attempts to see if this question should be added
		// by going thru entire list 20 X.
		for (int k = 0; k < 20; k++) {
			for (int i = 1; i <= 100; i++) {
				if (RandomMaker.isChosen(i)) {
					myQuestions.add(i);
				}
			}
		}
		for (int i = 0; i < 101; i++) {
			if (! myQuestions.contains(i)) {
				System.out.println("missing:" + i);
			}
		}
	}

	public void assertIsChosenManyTimes(int iExpected, int iTestNumber, int iIterations, int marginOfError) {
		int iChosen = 0;
		for (int i = 0; i < iIterations; i++) {
			if (RandomMaker.isChosen(iTestNumber)) {
				iChosen++;
			}
		}
		//double marginOfError = .15 * iExpected;
		//if (iExpected < 100) marginOfError = .25 * iExpected;
		//if (iExpected < 20) marginOfError = .5 * iExpected;
		//if (iExpected < 10) marginOfError = .75 * iExpected;
		
		assertEquals( 1.0 * iExpected, 1.0 * iChosen, marginOfError);
	}
}
