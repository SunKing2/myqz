package myqz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QzToJavaTest {
	
	QzToJava qz = new QzToJava();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadData1() {
		qz.loadData();
		assertEquals(8, qz.qCount);
		assertEquals("[0]", qz.fileNumDirty.toString());
		assertEquals("[mystuff.qz]", qz.fileNumFileName.toString());
		assertEquals("[8]", qz.fileNumQuestionCount.toString());
	}
	
	@Test
	public void testLoadData2() {
		qz.loadData();
		String sExpected = 
				"$gQCount	'8'	\n" + 
				"@gFileNum_Dirty	[ '0' ]	\n" + 
				"@gFileNum_FileName	[ 'mystuff.qz' ]	\n" + 
				"@gFileNum_QuestionCount	[ '8' ]	\n" + 
				"";

		assertObjectEqualsStringData(sExpected);
	}
	@Test
	public void testMungeData() {
		qz.loadData();
		qz.mungeData();
		String sExpected = 
				"$gQCount2	'8'	\n" + 
				"$gTotalRating	'612'	\n" + 
				"@gWhenAsked	[ '-15', '-15', '-15', '-15', '-15', '-15', '-15', '-15' ]	\n" + 
				"";

		assertObjectEqualsStringData(sExpected);
		
		
		assertQByRating(30, "1 " );
		assertQByRating(45, "2 " );
		assertQByRating(68, "3 " );
		assertQByRating(69, "0 " );
		assertQByRating(100, "4 5 6 7 " );
	}
	
	private void assertQByRating(int index, String expected) {
		assertEquals(expected, qz.qByRating.get(index));
	}

	private void assertObjectEqualsStringData(String sExpected) {
		boolean bSuccess = false;
		String msg = "";
		try {
			bSuccess = DataComparer.verifyFields(qz, sExpected);
		}
		catch (Exception exc) {
			System.out.println("exc:" + exc);
			msg = exc.toString();
		}
		assertTrue("oops, exc:" + msg, bSuccess);
	}
}
