package myqz;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OOQzTest2 {

	private OOQz ot = new OOQz();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ot.initialize();
		ot.mungeData();
	}
	@Test
	public void TestGotIt0() {
		ot.askQ(0);
		ot.GotIt(0);
		assertEquals(67, ot.getQuestions().get(0).getRating());
	}
	
	@Test
	public void TestGotIt1() {
		ot.askQ(0);
		ot.GotIt(0);
		ot.askQ(1);
		ot.GotIt(1);
		assertEquals(67, ot.getQuestions().get(1).getRating());
	}
	@Test
	public void TestReadFile() {
		List<Question> lis = ot.readFile();
		assertNotNull(lis);
		assertEquals(3, lis.size());
		Question q0 = lis.get(0);
		//Question q1 = lis.get(1);
		//Question q2 = lis.get(2);
		assertEquals("AQT", q0.getQuestion());
		assertEquals("QAT", q0.getAnswer());
		assertEquals(100, q0.getRating());
	}
	
	@Test
	public void TestOriginalFileContents() {
		doRead("mystuff.qz", new int[]{100, 100, 100});
	}

	@Test
	public void TestQuestionsToString() {
		// read questions from original file store into class
		List<Question> lis = ot.readFile();
		assertEquals(3,lis.size());
		// call method which returns class's questions as a string
		String sQuestions = OOQzUtils.questionsToString(ot.getQuestions());
		doCompareStringToModifiedFileData(new int[]{100, 100, 100}, sQuestions);
	}
	
	@Test
	public void testQuestionsToFile() {
		// read questions from original file store into class
		List<Question> lis = ot.readFile();
		assertEquals(3,lis.size());
		
		// call method to write class's questions to file
		ot.writeFile("myquestions.out");
		
		// read newly stored file into a string
		String sFileContents = OOQzUtils.readFile("myquestions.out");
		
		// compare string of file conents to expected;
		doCompareStringToOriginalFileData(sFileContents);
	}
	
	public void doRead(String fileName, int[] ratings) {

		String sActual = OOQzUtils.readFile(fileName);

		doCompareStringToModifiedFileData(ratings, sActual);
	}

	private void doCompareStringToOriginalFileData(String sActual) {
		this.doCompareStringToModifiedFileData(new int[]{100, 100, 100}, sActual);
	}
	private void doCompareStringToModifiedFileData(int[] ratings, String sActual) {
		String sOriginal = 
				"AQT	QAT	100	0	CO	\n" + 
				"IQS	QIS	100	0	CO	\n" + 
				"AEU	EAU	100	0	CO	";
		
		String sExpected = sOriginal;
		// original ratings were 100, replace each rating with passed param
		for (int r: ratings) {
			sExpected = sExpected.replaceFirst("100", ""+r);
		}
		
		assertEquals(sExpected, sActual);
	}
}
