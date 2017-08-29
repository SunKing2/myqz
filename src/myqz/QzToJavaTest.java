package myqz;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class QzToJavaTest {
	
	QzToJava qz = new QzToJava();

	private static final String QZ_INPUT_FILE = "mystuff.qz";
	private static final String QZ_OUTPUT_FILE = "myout.qz";
	private static final String EXPECTED_ALGORITHM0_FINAL_FILE_CONTENTS = 
			"GUV	GUV VUG	47	1503615679	CO	\n" + 
			"DIOOT	OOTID	21	1503615683	CO	\n" + 
			"MNOU	MUON	31	1503615681	CO	\n" + 
			"HQRSU	QURSH	100	1503606581	CO	\n" + 
			"NSY	SYN	67	1503615676	CO	\n" + 
			"AFLN	FLAN	67	1503615677	CO	\n" + 
			"DEEF	FEED	100	0	CO	\n" + 
			"AAFNU	FAUNA	67	1503615678	CO	\n" + 
			"";
	
	private static final String EXPECTED_ALGORITHM1_FINAL_FILE_CONTENTS =
			"GUV	GUV VUG	47	1503615679	CO	\n" + 
			"DIOOT	OOTID	100	1503495163	CO	\n" + 
			"MNOU	MUON	31	1503615681	CO	\n" + 
			"HQRSU	QURSH	46	1503615683	CO	\n" + 
			"NSY	SYN	67	1503615676	CO	\n" + 
			"AFLN	FLAN	67	1503615677	CO	\n" + 
			"DEEF	FEED	100	0	CO	\n" + 
			"AAFNU	FAUNA	67	1503615678	CO	\n" + 
			"";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadData1() {
		qz.loadData();
		assertEquals(8, qz.qCount);
		assertEquals("[0]", qz.fileNumDirty.toString());
		assertEquals("[" + QZ_INPUT_FILE + "]", qz.fileNumFileName.toString());
		assertEquals("[8]", qz.fileNumQuestionCount.toString());
	}
	
	@Test
	public void testLoadData2() {
		qz.loadData();
		/*
				"$gQCount	'8'	\n" + 
				"@gFileNum_Dirty	[ '0' ]	\n" + 
				"@gFileNum_FileName	[ '" + QZ_INPUT_FILE + "' ]	\n" + 
				"@gFileNum_QuestionCount	[ '8' ]	\n" + 
				"";
        */
		assertEquals(8, qz.qCount);
		assertEquals("[0]", qz.fileNumDirty.toString());
		assertEquals("[mystuff.qz]", qz.fileNumFileName.toString());
		assertEquals("[8]", qz.fileNumQuestionCount.toString());
	}
	@Test
	public void testMungeData() {
		qz.loadData();
		qz.mungeData();
		/*
				"$gQCount2	'8'	\n" + 
				"$gTotalRating	'612'	\n" + 
				"";
        */
		assertEquals(8, qz.qCount2);
		assertEquals(612, qz.totalRating);
		
		assertQByRating(30, "1 " );
		assertQByRating(45, "2 " );
		assertQByRating(68, "3 " );
		assertQByRating(69, "0 " );
		assertQByRating(100, "4 5 6 7 " );
		
		// should be 8 questions, all with -15 on whenAsked
		for (int i = 0; i < 8; i++) {
			assertEquals(-15, qz.questions.get(i).whenAsked);
		}
	}
	
	// test that the entire question set ran with algorithm 0
	@Test
	public void testDoRunQuizAlgorithm0() {
		qz.setAlgorithm(1.0, 0.0, 0.0, 0.0);
		qz.doRunQuiz(null);
		String sActualFileContents = "";
		try {
			sActualFileContents = QzUtils.readFile(QZ_OUTPUT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(EXPECTED_ALGORITHM0_FINAL_FILE_CONTENTS, sActualFileContents);
	}
	
	@Test
	//TODO uncomment test
	public void testDoRunQuizAlgorithm1() {
		qz.setAlgorithm(0.0, 1.0, 0.0, 0.0);
		qz.doRunQuiz(null);
		String sActualFileContents = "";
		try {
			sActualFileContents = QzUtils.readFile(QZ_OUTPUT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(EXPECTED_ALGORITHM1_FINAL_FILE_CONTENTS, sActualFileContents);
	}
	
	private void assertQByRating(int index, String expected) {
		assertEquals(expected, qz.qByRating.get(index));
	}
}
