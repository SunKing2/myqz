package qz;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;

/*
 * Uses TDD to reverse engineer qz.pl (John Chew).
 * Each of these many tests run qz.pl to generated "expected" strings.  
 * It captures stdout into a java String, as "expected" results for junit.
 * These tests generate 2 files to be used for each run of qz.pl:
 * 1. a .qz file (which, of course, every qz.pl run needs at least one)
 * 2. list of responses to questions that a user would type.  
 * File 2 is used as stdin to qz.pl for each test case. 
 */
public class QzTest1 {
	
	Qz qz = null;

	@Test
	public void testRunQzProgram1() throws IOException {
		
		String sData = 
			    "AQT	QAT	+100	0	C	\n" + 
			    "IQS	QIS	+100	0	C	\n" + 
			    "";
		
		// run qz multiple times, once for each line here:
		String[][] arrResponses = {
				{"", ""},
				{"qat", "qis"},
				{"qat", ""},
				{"", "qis"},
				{"bla", ""},
		};
		int i = 0;
		for (String[] sResponse: arrResponses)
		{
			qz = new Qz();
			assertGoodQzOutput("1. fail:" + i, sResponse, sData);
			i++;
		}
	}

	@Test
	public void testRunQzProgram2() throws IOException {
		
		String sData = 
			    "IQS	QIS	+100	0	C	\n" + 
			    "AQT	QAT	+100	0	C	\n" + 
			    "";
		
		// run qz multiple times, once for each line here:
		String[][] arrResponses = {
				{"", ""},
				{"qis", "qat"},
				{"qis", ""},
				{"", "qat"},
		};
		int i = 0;
		for (String[] sResponse: arrResponses)
		{
			qz = new Qz();
			assertGoodQzOutput("2. fail:" + i, sResponse, sData);
			i++;
		}
	}
	
	@Test
	public void testRunQzProgram3() throws IOException {
		
		String sData = 
			    "AQT	QAT	67	1504471490	C	\n" + 
			    "IQS	QIS	70	1504471501	C	\n" + 
			    "";
		
		// run qz multiple times, once for each line here:
		String[][] arrResponses = {
				{"", ""},
				{"qis", "qat"},
				{"qis", ""}, 
				{"", "qat"},

		};
		int i = 0;
		for (String[] sResponse: arrResponses)
		{
			qz = new Qz();
			assertGoodQzOutput("3. fail:" + i, sResponse, sData);
			i++;
		}
	}
	
	@Test
	public void testRunQzProgram4() throws IOException {
		
		String sData = 
			    "ADIQ	QADI QAID	31	1503314531	C	\n" + 
			    "IIKP	PIKI	100	1503606435	C	\n" + 
			    "AJNTU	JAUNT JUNTA	100	1503748624	C	\n" + 
			    "";
		
		// run qz multiple times, once for each line here:
		String[][] arrResponses = {
				{"", "", ""},
				{"piki", "jaunt junta", "qadi qaid"},
				{"", "jaunt junta", "qadi qaid"},
				{"piki", "", ""},
				{"piki", "jaunt junta", ""},

		};
		int i = 0;
		for (String[] sResponse: arrResponses)
		{
			qz = new Qz();
			assertGoodQzOutput("4. fail:" + i, sResponse, sData);
			i++;
		}
	}
	
	@Test
	public void testRunQzProgram13() throws IOException {
		
		String sData = 
			    "AQT	QAT	+100	0	C	\n" + 
				"CMW	CWM	+100	0	C	\n" + 
			    "IQS	QIS	+100	0	C	\n" + 
			    "";
		
		// run qz multiple times, once for each line here:
		String[][] arrResponses = {
				{"", "", ""},
				{"qat", "cmw", "qis"},
				{"qat", "", ""},
				{"", "", "qis"},
				{"bla", "cwm", "qis"},
		};
		int i = 0;
		for (String[] sResponse: arrResponses)
		{
			qz = new Qz();
			assertGoodQzOutput("13.fail:" + i, sResponse, sData);
			i++;
		}
	}
	// don't put any tests here

	private void assertGoodQzOutput(String message, String[] responses, String sData) throws IOException {
		String responsesAsString = "";
		for (String s: responses) {	
			responsesAsString += s + "\n";
		}
		String expected = runQz(sData, responsesAsString);
		String actual = qz.process(sData, responses);
		
		assertEquals(message, expected, actual);
		
		// now test to see if simulated output file gets created properly
		String sExpectedOutputFile = QzUtils.readFileAsString("maketestcases/mystuff.qz");
		assertEquals("file contents:" + message, sExpectedOutputFile, qz.questionsToString());
		
	}
	public static String runQz(String data, String responses) throws IOException{
		RunPerl rp = new RunPerl();
		rp.createInputDataFileForPerl("maketestcases/mystuff.qz", data);
		rp.createInputDataFileForPerl("maketestcases/inputdata", responses);
		
		rp.runProgram("maketestcases/runqzredirectedinput.sh", "", "", "");
		String actual = rp.getOutput();
		
		return actual;
	}

	// don't put any tests here
}
