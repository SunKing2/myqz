package qz;

import java.io.*;

public class RunPerl {
	
	private String sOutput = "";
	
	String getOutput() {
		return sOutput;
	}
	
	void runPerl(String perlFile) throws IOException{
		runProgram("perl", perlFile, "-phardest", "mystuff.qz");
	}
	
	void runProgram(String command, String parm1, String parm2, String parm3) throws IOException{
		Process process = new ProcessBuilder(command, parm1, parm2, parm3)
				.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

		sOutput = "";
		String line = "";
		while ((line = in.readLine()) != null) {
			sOutput = sOutput + line + '\n';
		}
		
		in.close();
		process.destroy();
	}
	
	void createInputDataFileForPerl(String fileName, String data) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(fileName);
		out.print(data);
		out.close();
	}
	
}
