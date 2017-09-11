package qz;

import java.util.*;

public class Qz {

	private List<QzQuestion> lisQuestions = new ArrayList<>();
	private int iTotalAsked = 0;
	private int iTotalCorrect = 0;

	// run a qz run on input data,
	// Use a set of strings as responses to the questions that qz will prompt for
	// each line of input data contains a field of question data, eg:
	// question\tanswer\trating\tage\tflags\tnotes
	public String process(String inputData, String[] responses) {
		QzStats stats = new QzStats();
		
		// add all the questions to the questions List
		int i = 0;
		for (String line : inputData.split("\\n")) {
			if (line.length() > 4) {
				lisQuestions.add(new QzQuestion(line, i++));
			}
		}
		// sort by hardest
		lisQuestions.sort((p1, p2) -> p2.rating.compareTo(p1.rating));
		
		String sResult = processResponses(lisQuestions, responses);

		sResult += stats.process(iTotalAsked, iTotalCorrect, lisQuestions, responses);
		
		return sResult;
	}
	
	// TODO this method is not functional
	// it modifies iTotalCorrect, iTotalAsked
	// modifies passed param: questions
	public String processResponses(List<QzQuestion> questions, String[] responses) {
		String sReturn = "";
		int i = 0;
		long now = (long)System.currentTimeMillis()/1000;
		for (String response : responses) {
			QzQuestion q = questions.get(i);
			int newRating = q.rating;
			sReturn += String.format("[%d] %s: %s\n", i + 1, q.question, response);
			boolean bCorrect = response.equalsIgnoreCase(q.answer);
			String sOldRatingString = q.unseen ? "new" : "" + q.rating;
			// TODO not functional
			iTotalAsked++;
			if (bCorrect) {
				String sAgeStuff = "never";
				if (q.age != 0) sAgeStuff = QzUtils.secondsToHuman(now - q.age);//sAgeStuff = "7 d";
				newRating = q.unseen ? 1 : (int) (q.rating * .667);
				sReturn += String.format("Correct.  (%s:%s-%d)\n", sAgeStuff, sOldRatingString, newRating);
				// TODO not functional
				iTotalCorrect++;
				// TODO not functional
				q.age = now;
			} 
			else {
				sReturn += String.format("The correct answer is '%s'  (%s-%s)\n", q.answer, sOldRatingString, "100");
				newRating = 100;
			}
			// TODO not functional, modifies param:
			q.unseen = false;
			// TODO not functional, modifies param:
			q.rating = newRating;
			i++;
		}
		return sReturn;
	}

	public String questionsToString() {
		String sReturn = "";
		// sort by original order in file
		lisQuestions.sort((p1, p2) -> p1.fileOrder.compareTo(p2.fileOrder));

		for (QzQuestion q: lisQuestions) {
			sReturn += 
					q.question + '\t' +
					q.answer + '\t' +
					q.rating + '\t' +
					q.age + '\t' + 
					q.flags + '\t' +
					"\n";
		}
		return sReturn;
	}
}