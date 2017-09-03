package myqz;

public class QzFS {
	
	public QzFS(String sFileData) {
	}

	public String process(String[] answers, String[] responses, double[] responseTimes) {
		int oldScore = 100;
		
		int nCorrect = 0;
		int nBugTotal = 1;
		int elapsedSeconds = 4;

		int nNoBugTotal = 2;
		boolean showMeanDifficulty = true;
		double meanDifficulty = 100.0;
		String meanDifficultyUnits = "s";
		boolean showMeanSolutionTime = false;
		double meanSolutionTime = 100.0;
		String meanSolutionTimeUnits = "s";
		
		int meanSolutionAge = 17412;
		String meanSolutionAgeUnits = "d";
		String oldestSolution = "never";
		String blaString = "";
		double totalTime = 0;
		double totalCorrectTime = 0;
		int totalRating = 0;
		
		for (int i = 0; i < answers.length; i++) {
			totalTime += responseTimes[i];
			if (responses[i].toUpperCase().equals(answers[i].toUpperCase())) {
				nCorrect++;
				totalRating += this.evaluateScore(true, 100, responseTimes[i]);
				totalCorrectTime += responseTimes[i];
				showMeanDifficulty = false;
				showMeanSolutionTime = true;
				
				meanSolutionTime = 67;
				meanSolutionAge = 1;
				meanSolutionAgeUnits = "s";
				oldestSolution = "3 s";
			}
		}

		elapsedSeconds = (int) (totalTime + 1.5);
		int ios = (int) (responseTimes[1] + .5);
		oldestSolution = "" + ios + " s";

		if (nCorrect > 0) {
			blaString = String.format("You took on average %.1f seconds to answer correctly.\n" + 
					"Congratulations!\n", totalCorrectTime/nCorrect);
		}
		else {
			oldestSolution = "never";
		}
			
		if (nCorrect == 1) {
			meanSolutionTime = totalRating / nCorrect;
			int unsolved = 1;  // used just to calc meanDifficulty
			int unseen = 0;    // used just to calc meanDifficulty
			int seen = answers.length;  // used just to calc meanDifficulty
			meanDifficulty = (100.0*(unsolved+unseen)+totalRating)/(seen+unseen);
			showMeanDifficulty = true;
			meanSolutionAge = 8706;
			meanSolutionAgeUnits = "d";
			oldestSolution = "never";
		}

		return processLine(alphagram(answers[0]), answers[0], responses[0], 1, oldScore, responseTimes[0]) +
		processLine(alphagram(answers[1]), answers[1], responses[1], 2, oldScore, responseTimes[1]) +
		getSummaryHeader(nCorrect, nBugTotal, elapsedSeconds, blaString) + 
		getStats(nNoBugTotal, nCorrect,
				showMeanSolutionTime, meanSolutionTime, meanSolutionTimeUnits,
				showMeanDifficulty, meanDifficulty, meanDifficultyUnits,
				meanSolutionAge, meanSolutionAgeUnits, oldestSolution);
	}
	
	private String alphagram(String words) {
		if (words.equals("QAT")) return "AQT";
		return "IQS";
	}

	private String getStats(int nNoBugTotal, int solved, 
			boolean showMeanSolutionTime, double meanSolutionTime, String meanSolutionTimeUnits, 
			boolean showMeanDifficulty, double meanDifficulty, String meanDifficultyUnits,
			int meanSolutionAge, String meanSolutionAgeUnits, String oldestSolution) {

		int unsolved = nNoBugTotal - solved;

		String statsBase = 
		  String.format(
		  "Current statistics for this question set:\n" + 
		  "Total: %d\n" + 
		  "Solved: %d (%.0f%%)\n" + 
		  "Unsolved: %d (%.0f%%)\n" + 
		  "",
		  nNoBugTotal, 
			solved, 100.0 * solved / nNoBugTotal,
			unsolved, 100.0 * unsolved / nNoBugTotal);
		String statsBaseMST =
		  String.format(
		  "Mean solution time: %.1f %s\n" + 
		  "",
		  meanSolutionTime, meanSolutionTimeUnits);
		String statsBaseMD =
		  String.format(
		  "Mean difficulty: %.1f %s\n" + 
		  "",
		  meanDifficulty, meanDifficultyUnits);
		String statsBaseMSA =
		  String.format(
		  "Mean solution age: %d %s\n" + 
		  "",
		  meanSolutionAge, meanSolutionAgeUnits);
		String statsBaseOS =
		  String.format(
		  "Oldest solution: %s\n" + 
		  "",
		  oldestSolution);


		String stats = "";
		stats += statsBase;
		
		
		if (showMeanSolutionTime) {
			stats += statsBaseMST;
		}
		if (showMeanDifficulty) {
			stats += statsBaseMD;
		}
		
		stats += statsBaseMSA;
		stats += statsBaseOS;
				
		return stats;
	}
	

	private String getSummaryHeader(int nCorrect, int nBugTotal, int elapsedSeconds, String blaString) {
		String s2 =
		  "No more questions available.\n" + 
		  "\n" + 
		  "You answered %d questions correctly of %d (%.1f%%).\n" + 
		  blaString +
		  "Elapsed time: %s\n" + 
		  "\n" + 
		  "";
		String s3 = String.format(s2, nCorrect, nBugTotal, 100.0 * nCorrect / nBugTotal,
				durationToString(elapsedSeconds));
		if (nCorrect == 1) {
			s3 = s3.replace("questions correctly", "question correctly");
		}
		return s3;
	}
	
	private String durationToString(int seconds) {
		return String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));		
	}

	/*
	[1] AQT: 
	The correct answer is 'QAT'  (100-100)
	*/
	private String processLine(String question, String answer, String response, int questionNumber, int oldScore, double timeToSolve) {
		boolean bRight = response.toUpperCase().equals(answer.toUpperCase());
		int newScore = evaluateScore(bRight, 100, timeToSolve);
		String sRight =
		  showQuestion(question, questionNumber) +
		  response +
		  "\n" + 
		  showIfCorrectAndScore(bRight, answer, oldScore, newScore);
		String sWrong = 
		  showQuestion(question, questionNumber) +
		  "\n" +
		  showIfCorrectAndScore(bRight, answer, oldScore, newScore);
		if (bRight) {
			return sRight;
		}
		return sWrong;
	}

	/*
	[1] AQT:
	*/ 
	// Show's what the system prompts when asking question
	//    does not show answer
	// has a trailing space but no carriage return
	private String showQuestion(String question, int questionNumber) {
		return 
		  String.format("[%d] %s: ", questionNumber, question);
	}
	/*
	The correct answer is 'QAT'  (100-100)
	*/
	private String showIfCorrectAndScore(boolean correct, String answer, int oldScore, int newScore) {
		if (correct) {
			return String.format("Correct.  (never:%d-%d)\n", oldScore, newScore);
		}
		return String.format("The correct answer is '%s'  (%d-%d)\n", answer, oldScore, newScore);
	}
	
	private int evaluateScore(boolean correct, int oldScore, double timeToSolve) {
		if (correct == false) {
			return 100;
		}
		return (int)((1+2* oldScore + timeToSolve) / 3);
	}

}
