package myqz2;

import java.io.IOException;

public class TestStaticMethods {
	public static String runQz(String data, String responses) throws IOException{
		RunPerl rp = new RunPerl();
		rp.createInputDataFileForPerl("maketestcases/mystuff.qz", data);
		rp.createInputDataFileForPerl("maketestcases/inputdata", responses);
		
		rp.runProgram("maketestcases/runqzredirectedinput.sh", "", "", "");
		String actual = rp.getOutput();
		
		return actual;
	}

}
