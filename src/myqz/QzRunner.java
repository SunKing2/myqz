package myqz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

public class QzRunner {

	public static void main(String[] args) throws Exception {
		QzRunner qr = new QzRunner();
		qr.doIt();
	}

	private void doIt() throws IOException, InterruptedException {
		ProcessBuilder pb;
		Process process;
		pb = new ProcessBuilder("cat");
		pb.redirectInput(Redirect.from(new File("test2.dat")));
		process = pb.start();
		int errCode = process.waitFor();
		System.out.println("errCode:" + errCode);
		InputStream output = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(output));
		String line = br.readLine();
		System.out.println("line:" + line);
		br.close();
	}
}
