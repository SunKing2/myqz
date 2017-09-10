package myqz2;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

/*
 * Uses TDD to reverse engineer qz.pl (John Chew).
 * These test run qz.pl (many many times) to generated "expected" strings.  
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

		};
		int i = 0;
		for (String[] sResponse: arrResponses)
		{
			qz = new Qz();
			assertGoodQzOutput("3. fail:" + i, sResponse, sData);
			i++;
		}
	}
	
	
	
//  ======================  not here yet
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
		String expected = TestStaticMethods.runQz(sData, responsesAsString);
		String actual = qz.process(sData, responses);
		
		assertEquals(message, expected, actual);
	}
	// don't put any tests here
}
