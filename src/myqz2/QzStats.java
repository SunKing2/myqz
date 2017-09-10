package myqz2;

import java.util.*;

public class QzStats {
	public int iTotalCorrect = 0;
	public int iTotalAsked = 0;
	private QzTemplate template = new QzTemplate();

	public String sreport = "";
	public String process(List<QzQuestion>lisQuestions, String[] responses) {		
			
		// start of jc code
		int solved = 0;
		int totalRating = 0;
		int unseen = 0;
		int unsolved = 0;
		int age_sum = 0;
		int now = (int)System.currentTimeMillis()/1000;
		int count = 0;  // lvb says this is not needed, should use size instead
			
		// calculate stats
		{
		  for (int qIndex = 0; qIndex < lisQuestions.size(); qIndex++) {
		    age_sum += lisQuestions.get(qIndex).age;
		    count++;
		    int rating = lisQuestions.get(qIndex).rating;
		    if (lisQuestions.get(qIndex).unseen) { unseen++; }
		    else if (rating == 100) { unsolved++; }
		    else { solved++; totalRating += rating; }
		  } 
		}
		// print report
		int seen = solved + unsolved;
		String total = "" + (unseen+seen);
		boolean bPrintUnseen = unseen > 0? true: false;
		String unseenpercent = "" + (0.5+100*(unseen/(seen+unseen)));
		boolean bPrintSolvedPercent = seen > 0 ? true: false; 
		String solvedpercent = bPrintSolvedPercent? "" + (int)(0.5+100*solved/seen) : "n/a";
		boolean bPrintUnSolvedPercent = seen > 0 ? true: false; 
		String unsolvedpercent = bPrintUnSolvedPercent? "" + (int)(0.5+100*unsolved/seen) : "n/a";
		boolean bPrintMeanSolutionTime = solved > 0? true: false;
		String meansolutiontime = bPrintMeanSolutionTime? "" + (totalRating/solved) : "n/a";
		boolean bPrintMeanDifficulty = (unsolved > 0 || unseen > 0) ? true: false;
		String meandifficulty = "" + ((100*(unsolved+unseen)+totalRating)/(seen+unseen));
		boolean bPrintMeanSolutionAge = count > 0? true: false;
		String meansolutionage = "" + ((int)(now - 1.0 * age_sum/count));
		long t = 0;
		for (QzQuestion quest: lisQuestions) {
			if (quest.age < t) t = quest.age;
		}
		String oldestsolution = "" + (t > 0? (now -t) : "never");
		// end of jc code
		
		// prepare vars for sub s lvb code
		int gQCorrect = 2; // fake
		int promptQord = 2; // fake
		int gTotalTime = 0; // fake
		long time = System.currentTimeMillis() / 1000; // maybe put this as first thing in method
		long gSessionStart = time;  // fake
		// begin jc code sub S
		String questionplural = gQCorrect == 1 ? "" : "s";
		boolean bShowAnsweredPercent = promptQord > 0;
		String answeredpercent = "" + (100.0 * gQCorrect / promptQord);
		boolean bPrintYouTookAnAverage = gQCorrect > 0;
		
		String youtookonaverage = gQCorrect > 0 ? "" +  (gTotalTime / gQCorrect) : "";
		boolean bPrintCongratulations = promptQord > 0 && 1.0 * gQCorrect / promptQord > 0.9;
		long elapsed = time - gSessionStart;
		String elapsedtime = String.format("%d:%02d:%02d", 
		    (int)(elapsed/3600.0), (int)(elapsed/60.0) % 60, elapsed % 60);
		// end jc code sub S

		template.addReplacement("answered", "" + gQCorrect);
		template.addReplacement("questionplural", questionplural);
		template.setStringVisibility(3, bShowAnsweredPercent);
		template.addReplacement("answeredpercent", answeredpercent);
		template.addReplacement("youtookonaverage", youtookonaverage);
		template.addReplacement("elapsedtimes", elapsedtime);
		template.addReplacement("totalm1", "" + promptQord);
		template.setStringVisibility(5, bPrintYouTookAnAverage);
		template.setStringVisibility(6, bPrintCongratulations);
		template.setStringVisibility(1, bPrintUnseen);
		template.setStringVisibility(12, bPrintUnseen);
		template.addReplacement("solved", "" + solved);
		template.addReplacement("solvedpercent", "" + solvedpercent);
		template.addReplacement("unsolved", "" + unsolved);
		template.addReplacement("unsolvedpercent", "" + unsolvedpercent);
		template.setStringVisibility(15, bPrintMeanDifficulty);
		template.setStringVisibility(16, bPrintMeanSolutionTime);
		template.addReplacement("meansolutiontime", meansolutiontime);
		template.addReplacement("meansolutionage", meansolutionage);
		template.addReplacement(oldestsolution, oldestsolution);
			
		String sResult = "";
		sResult = template.getResults();
		return sResult;
	}
}
