package qz;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import org.junit.Test;

public class RunPerlTest {

	private RunPerl rp = new RunPerl();
	private static final String shellProgramQz = "maketestcases/runqzredirectedinput.sh";
	private static final String temporaryFileName = "maketestcases/runperltest.tmp";
	private static final String qzInputFileName = "maketestcases/mystuff.qz";
	
	@Test
	public void testCreateInputDataFileForPerl() throws IOException {
		String sData = "good day\nbig world\n";
		String sFileName = temporaryFileName;
		rp.createInputDataFileForPerl(sFileName, sData);
		String sActual = readFile(sFileName);
		assertEquals(sData, sActual);
	}
	
	@Test
	public void testRunQzProgram() throws IOException {
		String sData = 
		    "AQT	QAT	+100	0	C	\n" + 
		    "IQS	QIS	+100	0	C	\n" + 
		    "";
		rp.createInputDataFileForPerl(qzInputFileName, sData);
		String responses = "qat\nqis\n";
		rp.createInputDataFileForPerl("maketestcases/inputdata", responses);
		
		rp.runProgram(shellProgramQz, "", "", "");
		String actual = rp.getOutput();
		
		String expected = 
			"[1] AQT: qat\n" + 
			"Correct.  (never:new-1)\n" + 
			"[2] IQS: qis\n" + 
			"Correct.  (never:new-1)\n" + 
			"No more questions available.\n" + 
			"\n" + 
			"You answered 2 questions correctly of 1 (200.0%).\n" + 
			"You took on average -0.4 seconds to answer correctly.\n" + 
			"Congratulations!\n" + 
			"Elapsed time: 0:00:00\n" + 
			"\n" + 
			"Current statistics for this question set:\n" + 
			"Total: 2\n" + 
			"Solved: 2 (100%)\n" + 
			"Unsolved: 0 (0%)\n" + 
			"Mean solution time: 1.0 s\n" + 
			"Mean solution age: 0 s\n" + 
			"Oldest solution: 0 s\n" + 
			"";
		
		assertEquals(expected, actual);
	}
	
	
	static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
