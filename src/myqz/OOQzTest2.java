package myqz;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OOQzTest2 {

	private OOQz ot = new OOQz();

	private String sOriginalFileData = 
			"AQT	QAT	100	0	CO	\n" + 
			"IQS	QIS	100	0	CO	\n" + 
			"AEU	EAU	100	0	CO	";

	private static final String DEFAULT_QUIZ_FILE_NAME = "mystuff.qz";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestReadFile() {
		List<Question> lis = ot.readFile(DEFAULT_QUIZ_FILE_NAME);
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
	public void TestOriginalFileContents() throws Exception {
		doRead("mystuff.qz", new int[]{100, 100, 100});
	}

	@Test
	public void TestQuestionsToString() {
		// read questions from original file store into class
		List<Question> lis = ot.readFile(DEFAULT_QUIZ_FILE_NAME);
		assertEquals(3,lis.size());
		// call method which returns class's questions as a string
		String sQuestions = OOQzUtils.questionsToString(ot.getQuestions());
		doCompareStringToModifiedFileData(new int[]{100, 100, 100}, sQuestions);
	}
	
	@Test
	public void testQuestionsToFile() throws Exception {
		// read questions from original file store into class
		List<Question> lis = ot.readFile(DEFAULT_QUIZ_FILE_NAME);
		assertEquals(3,lis.size());
		
		// call method to write class's questions to file
		ot.writeFile("myquestions.out");
		
		// read newly stored file into a string
		String sFileContents = OOQzUtils.readFile("myquestions.out");
		
		// compare string of file conents to expected;
		doCompareStringToOriginalFileData(sFileContents);
	}
	

	// no tests below this line ---------------------------
	private void doRead(String fileName, int[] ratings) throws Exception {

		String sActual = OOQzUtils.readFile(fileName);

		doCompareStringToModifiedFileData(ratings, sActual);
	}

	private void doCompareStringToOriginalFileData(String sActual) {
		this.doCompareStringToModifiedFileData(new int[]{100, 100, 100}, sActual);
	}
	private void doCompareStringToModifiedFileData(int[] ratings, String sActual) {
		String sExpected = sOriginalFileData;
		// original ratings were 100, replace each rating with passed param
		for (int r: ratings) {
			sExpected = sExpected.replaceFirst("100", ""+r);
		}
		
		assertEquals(sExpected, sActual);
	}
	// no tests above this line ---------------------------
}
