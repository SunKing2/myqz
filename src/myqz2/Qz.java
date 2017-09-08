package myqz2;

import java.util.*;

public class Qz {

	private QzStats stats = new QzStats();

	// run a qz run on input data,
	// Use a set of strings as responses to the questions that qz will prompt for
	// each line of input data contains a field of question data, eg:
	// question\tanswer\trating\tage\tflags\tnotes
	public String process(String inputData, String[] responses) {

		List<QzQuestion> lisQuestions = new ArrayList<>();
		
		// add all the questions to the questions List
		for (String line : inputData.split("\\n")) {
			if (line.length() > 4) {
				lisQuestions.add(new QzQuestion(line));
			}
		}
		// sort by hardest		
		lisQuestions.sort((p1, p2) -> p2.rating.compareTo(p1.rating));
		
		String sResult = processResponses(lisQuestions, responses);
		
		sResult += stats.process(lisQuestions, responses);
		
		return sResult;
	}
	
	// TODO this method is not functional
	// it modifies the stats.iTotalCorrect
	public String processResponses(List<QzQuestion> questions, String[] responses) {
		String sReturn = "";
		int i = 0;
		for (String response : responses) {
			QzQuestion q = questions.get(i);
			sReturn += String.format("[%d] %s: %s\n", i + 1, q.question, response);
			boolean bCorrect = response.equalsIgnoreCase(q.answer);
			String sOldRatingString = q.unseen ? "new" : q.srating;
			if (bCorrect) {
				sReturn += "Correct.  (never:new-1)\n";
				stats.iTotalCorrect++;
			} 
			else {
				sReturn += String.format("The correct answer is '%s'  (%s-%s)\n", q.answer, sOldRatingString, "100");
			}
			i++;
		}
		return sReturn;
	}
}