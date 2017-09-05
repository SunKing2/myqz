package myqz;
import java.util.*;

public class QzFS {
	
	public QzFS(String sFileData) {
	}
	
	public static final boolean REMOVE_DECIMAL = true;
	public static final boolean UNSEEN_FALSE = false;
	public static final boolean UNSEEN_TRUE = true;
	
	public String process(int startTime, int endTime, int algorithm, String[] answers, int[] ratings, int[] ages, 
			String[] responses, double[] netResponseTimes, int[] absoluteResponseTimes) {
		
		QzFSStats2 statsObject = new QzFSStats2(endTime);

		// 0 = show questions in order of hardest first
		if (algorithm == 0) {
			// sort, rating descending, 3 arrays in parallel by quickest dirtiest way possible
			class DD {String answer; int rating; int age; // throwaway class
			DD(String answer, int rating, int age) {this.answer=answer;this.rating=rating;this.age=age;} int getRating() {return rating;}}
			List<DD> dds = new ArrayList<>();
			for (int i = 0; i < answers.length; i++) {
				dds.add(new DD(answers[i], ratings[i], ages[i]));
			}
			dds.sort(Comparator.comparingInt(DD::getRating).reversed());
			for (int i = 0; i < dds.size(); i++) {
				DD di = dds.get(i);
				answers[i] = di.answer; ratings[i] = di.rating; ages[i] = di.age;
			}
		}

		int gQCorrect = 0;
		// You answered x questions correctly has a bug in qz.pl that shows 1 too few 
		int promptQord = answers.length - 1;
		double gTotalTime = 0.0;
		
		String sRet = "";
		
		for (int i = 0; i < answers.length; i++) {
			sRet += showQuestion(QzFSUtils.firstWordAlphagram(answers[i]), i + 1);
			sRet += showResponse(responses[i]);
			boolean correct = responses[i].equalsIgnoreCase(answers[i]);
			int newAge = 0;
			int newRating = 0;
			if (correct) {
				gQCorrect++;
				gTotalTime += netResponseTimes[i];
				newRating = getNewRating(correct, ratings[i], netResponseTimes[i]);
				String sAgeComment = getAgeComment(correct, absoluteResponseTimes[i], ages[i]);
				sRet += showCorrect(ratings[i], newRating, sAgeComment);
				newAge = absoluteResponseTimes[i];
			}
			else {
				newAge = ages[i];
				newRating = 100;
				sRet += showIncorrect(answers[i], ratings[i], newRating);
			}
			statsObject.addQ(newRating, newAge, UNSEEN_FALSE);
		}
		String summary = statsObject.summary(gQCorrect, promptQord, gTotalTime, startTime);
		String stats = statsObject.doListStats();
		
		sRet = sRet +
				"No more questions available.\n" +
				summary +
				"\nCurrent statistics for this question set:\n" +
				stats + 
				"\n";
		return sRet;
	}
	
	private String getAgeComment(boolean correct, int timeOfAnswer, int oldAge) {
		String sReturn = "never";
		if (oldAge > 0) {
			sReturn = QzFSUtils.secondsToHumanTime(timeOfAnswer - oldAge, REMOVE_DECIMAL);
		}
		return sReturn;
	}
	
	private int getNewRating(boolean correct, int oldRating, double timeToSolve) {
		if (correct == false) {
			return 100;
		}
		return (int)((1 + 2.0 * oldRating + timeToSolve) / 3.0);
	}
	
	/*
	[1] AQT:
	*/ 
	// Show's what the system prompts when asking question, (shows no response,  no \n)
	private String showQuestion(String question, int questionNumber) {
		return 
		  String.format("[%d] %s: ", questionNumber, question);
	}

	// shows what the user typed as a response to a question (includes \n)
	public String showResponse(String response) {
		return response + "\n";
	}
	
	private String showCorrect(int oldRating, int newRating, String sAgeComment) {
		String sReturn;
		sReturn =  String.format("Correct.  (%s:%d-%d)\n", sAgeComment, oldRating, newRating);
		return sReturn;
	}
	
	public String showIncorrect(String realAnswer, int oldRating, int newRating) {
		return String.format("The correct answer is '%s'  (%d-%d)\n", realAnswer, oldRating, newRating);
	}
	
}
