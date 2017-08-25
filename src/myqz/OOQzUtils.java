package myqz;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OOQzUtils {
	
	// a generic method which reads a file as a string
	// it's not really related specifically to this class
	public static String readFile(String fileName) throws Exception {
		String sReturn = "";
		
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			sReturn = new String(encoded, Charset.defaultCharset());
		}
		catch (Exception exc) {
			System.out.println("readFile exc:" + exc);
			throw new Exception("" + exc);
		}
		return sReturn;
	}
	public static String questionsToString(List<Question> questions) {
		String sReturn = "";
		for (Question q: questions) {
			sReturn += 
					q.getQuestion() + '\t' +
					q.getAnswer() + '\t' +
					q.getRating() + '\t' + "0\tCO\t\n";
		}
		//trim off trailing \n
		sReturn = sReturn.substring(0, sReturn.length()-1);

		return sReturn;
	}
}
