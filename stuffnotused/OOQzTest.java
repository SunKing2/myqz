package myqz;

import static org.junit.Assert.*;
import org.junit.Test;

public class OOQzTest {

	/*
	private String sOriginalFileData = 
			"AQT	QAT	100	0	CO	\n" + 
			"IQS	QIS	100	0	CO	\n" + 
			"AEU	EAU	100	0	CO	";
	 */
	
	private OOQz qz = new OOQz();
	private static final String DEFAULT_QUIZ_FILE_NAME = "mystuff.qz";
	
	@Test
	public void testInitialClassState() {
		assertEquals(2, qz.getQuestions().size());
	}
	
	@Test
	public void testReadFile() {
		qz.readFile(DEFAULT_QUIZ_FILE_NAME);
		assertEquals(3, qz.getQuestions().size());
		assertEquals("AQT", qz.getQuestions().get(0).getQuestion());
		assertEquals("QIS", qz.getQuestions().get(1).getAnswer());
		assertEquals(100, qz.getQuestions().get(0).getRating());
		assertEquals(100, qz.getQuestions().get(2).getRating());
	}
	
	// test if can read a file downloaded from chew's site
	@Test
	public void testReadFileChew() {
		qz.readFile("700.qz");
		assertEquals(100, qz.getQuestions().size());
		assertEquals("aeilnot", qz.getQuestions().get(2).getQuestion());
		assertEquals("elation toenail", qz.getQuestions().get(2).getAnswer());
		assertEquals(100, qz.getQuestions().get(2).getRating());
	}

	// test if can read a newly downloaded
	// truncated file that I ran thru once, making dirty
	// and answering about half the questions right
	@Test
	public void testReadFileChewPartDirty() {
		qz.readFile("700-part-dirty.qz");
		assertEquals(5, qz.getQuestions().size());
		assertEquals("adeinor", qz.getQuestions().get(0).getQuestion());
		assertEquals("aneroid", qz.getQuestions().get(0).getAnswer());
		assertEquals(1, qz.getQuestions().get(0).getRating());
	}

	@Test
	public void testWriteFile() throws Exception {
		// populate it with 3 questions (read them from original file)
		qz.readFile(DEFAULT_QUIZ_FILE_NAME);
		qz.writeFile("deleteme.qz");
		fileEquals("mystuff.qz", "deleteme.qz");
	}
	
	private void fileEquals(String file1, String file2) throws Exception {
		
		String s1 = OOQzUtils.readFile(file1);
		String s2 = OOQzUtils.readFile(file2);
		assertEquals(s1, s2);
	}
}
