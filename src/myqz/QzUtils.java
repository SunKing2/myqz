package myqz;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class QzUtils {
	
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
					q.getRating() + '\t' +
					q.getAge() + '\t' + 
					"CO\t\n";
		}
		//trim off trailing \n
		//sReturn = sReturn.substring(0, sReturn.length()-1);

		return sReturn;
	}
	
	public static void chooseWord(List<Question> questions) {

		Map<String, Integer> map = new HashMap<>();
		
		int iChosen = 0;
		while (iChosen < 10000) {
			Question quest = getOneRandomQuestion(questions);
			if (quest != null) {
				int iCurrentCount = 0;
				Integer result = map.get(quest.question);
				if (result != null) iCurrentCount = result;
				map.put(quest.question, iCurrentCount + 1);
				iChosen++;
			}
			else {
				System.out.println("null");
			}
		}
		System.out.println(map);
	}
	private static Question getOneRandomQuestion(List<Question> questions) {
		int totalRating = 0;
		
		for (Question quest: questions) {
			totalRating += quest.rating;
		}
		
		Question qReturn = null;
		for (int i = 0; i < 20; i++) {
			for (Question quest: questions) {
				double ran = Math.random();
				double rating = quest.rating;
				double rateMe = rating / totalRating;
				if (ran < rateMe) {
					//System.out.println("" + quest.question);
					return quest;
				}
			}
		}
		return qReturn;
	}
	public static void main(String[] args) {
		List<Question> q = new ArrayList<>();
		q.add(new Question("DOOIT", "", 30 ));
		q.add(new Question("MNOU", "", 45 ));
		q.add(new Question("GUV", "", 69 ));
		q.add(new Question("HQRSU", "", 68 ));
		q.add(new Question("AAFNU", "", 100 ));
		q.add(new Question("AFLN", "", 100 ));
		q.add(new Question("DEEF", "", 100 ));
		q.add(new Question("NSY", "", 100 ));
		
		chooseWord(q);
	}
}
