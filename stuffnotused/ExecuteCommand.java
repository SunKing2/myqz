package myqz;

import java.io.*;

public class ExecuteCommand {

	private static String[] sResponses = { "qat", "idunno", "shit", "", "", "", "", "", "", "", "", "" };

	public static void main(String[] argv) throws Exception {

		Process process0 = new ProcessBuilder("cp", "mystuff.qz.bak", "mystuff.qz")
				// "cat", "qztmp.pl")
				.start();

		Process process = new ProcessBuilder("perl", "qztmp.pl", "-phardest", "mystuff.qz")
				// "cat", "qztmp.pl")
				.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		PrintWriter out = new PrintWriter(process.getOutputStream());

		String line;
		System.out.println("Output of running program is:");

		int i = 0;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
			out.flush();
			System.out.flush();
			Thread.sleep(1000);
			if (line.charAt(0) == '[') {
				out.println(sResponses[i]);
				System.out.println(sResponses[i++]); // show it in output too
			}
		}
	}
}
