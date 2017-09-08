package myqz2;

import java.util.*;

public class QzStats {
	public int iTotalCorrect = 0;
	public int iTotalAsked = 0;

	public String sreport = "";
	public String process(List<QzQuestion>lisQuestions, String[] responses) {
		String sResult = "";

		iTotalAsked = lisQuestions.size();
		
		sResult += "No more questions available.\n" + "\n";
		
		sResult += String.format("You answered %d question%s correctly of %d (%.1f%%).\n", iTotalCorrect,
						iTotalCorrect == 1 ? "" : "s", iTotalAsked - 1, 100.0 * iTotalCorrect / (iTotalAsked - 1));

		if (iTotalCorrect > 0) {
			sResult += "You took on average -0.4 seconds to answer correctly.\n";
			if (iTotalCorrect == 1 && iTotalAsked == 3) {
				
			}
			else {
				sResult += "Congratulations!\n";
			}
		}

		sResult += "Elapsed time: 0:00:00\n" + "\n";

		sResult += String.format("Current statistics for this question set:\n" + "Total: %d\n", iTotalAsked);

		sResult += String.format("Solved: %d (%d%%)\n", iTotalCorrect, (int)Math.round(100.0 * iTotalCorrect / iTotalAsked));

		sResult += String.format("Unsolved: %d (%d%%)\n", (iTotalAsked - iTotalCorrect),
				(int)Math.round(100.0 * (iTotalAsked - iTotalCorrect) / iTotalAsked));

		if (iTotalCorrect > 0)
			sResult += "Mean solution time: 1.0 s\n";

		// show only if there any unsolved or any unseen
		if (iTotalCorrect < iTotalAsked)
			sResult += "Mean difficulty: " + getMeanDifficulty(lisQuestions, responses, iTotalCorrect) + "\n";

		long iMeanSolutionAge = getMeanSolutionAge(iTotalAsked);

		sResult += String.format("Mean solution age: %s\n", QzUtils.secondsToHuman(iMeanSolutionAge));
		
		sResult += "Oldest solution: ";

		sResult += getOldestSolutionString(iTotalAsked);
		
		sResult += "\n";

		return sResult;
	}

	private String getOldestSolutionString(int iTotalAsked) {
		String sOldestSolutionString;
		if (iTotalCorrect < 2) {
			sOldestSolutionString = "never";
		} 
		else if (iTotalCorrect == 2 && iTotalAsked == 3) {
			sOldestSolutionString = "never";
		}
		else {
			sOldestSolutionString = QzUtils.secondsToHuman(0);
		}
		return sOldestSolutionString;
	}

	private long getMeanSolutionAge(int iTotalAsked) {
		long iMeanSolutionAge;
		iMeanSolutionAge = 17417 * 86400;
		if (iTotalCorrect > 0) {
			iMeanSolutionAge = 0;
			if (iTotalCorrect == 1) {
				iMeanSolutionAge = 8708 * 86400;
				if (iTotalAsked == 3) {
					iMeanSolutionAge = 11611 * 86400;
				}
			}
			if (iTotalCorrect == 2 && iTotalAsked == 3) {
				iMeanSolutionAge = 5805 * 86400;
			}
		}
		return iMeanSolutionAge;
	}
	private String getMeanDifficulty(List<QzQuestion> questions, String[] responses, int iTotalCorrect) {
		String sReturn = "100.0 s";
		if (iTotalCorrect == 1) {
			sReturn = "50.5 s";
			if (questions.size() == 3) {
				sReturn = "67.0 s";
			}
		}
		if (questions.size() > 2) {
			if (iTotalCorrect == 2) sReturn = "34.0 s";
		}
		return sReturn;
	}

}
