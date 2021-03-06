import java.util.Arrays;
import java.util.Collections;

import myqz.QzFSUtils;

//       ===================== ignore below this line ==============	
	public String xprocess(int startTime, int endTime, int algorithm, String[] answers, int[] ratings, int[] ages, String[] responses, double[] netResponseTimes) {

		// sort by hardest first:
		if (algorithm == 0 && ratings[1] > ratings[0]) {
			// sort each array by hardest first (fake it)
			Collections.reverse(Arrays.asList(answers));
			int tmp = ratings[0]; ratings[0] = ratings[1]; ratings[1] = tmp;
			tmp = ages[0]; ages[0] = ages[1]; ages[1] = tmp;
		}
		
		int nCorrect = 0;
		int nBugTotal = 1;
		int elapsedSeconds = 4;

		int nNoBugTotal = 2;
		boolean showMeanDifficulty = true;
		double meanDifficulty = 100.0;
		//String meanDifficultyUnits = "s";
		boolean showMeanSolutionTime = false;
		double meanSolutionTime = 100.0;
		//String meanSolutionTimeUnits = "s";
		
		int meanSolutionAge = 1504396800;
		//String meanSolutionAgeUnits = "d";
		String oldestSolution = "never";
		String sYourAverageCongrats = "";
		double totalTime = 0;
		double totalCorrectTime = 0;
		int totalRating = 0;
		
		for (int i = 0; i < answers.length; i++) {
			totalTime += netResponseTimes[i];
			if (responses[i].toUpperCase().equals(answers[i].toUpperCase())) {
				nCorrect++;
				totalRating += this.evaluateRating(true, ratings[i], netResponseTimes[i]);
				totalCorrectTime += netResponseTimes[i];
				showMeanDifficulty = false;
				showMeanSolutionTime = true;
				
				meanSolutionTime = 67;
				meanSolutionAge = 1;
				//meanSolutionAgeUnits = "s";
				oldestSolution = "3 s";
			}
		}

		elapsedSeconds = (int) (totalTime + 1.0);
		int ios = (int) (netResponseTimes[1] + .5);
		oldestSolution = "" + ios + " s";

		if (nCorrect > 0) {
			sYourAverageCongrats = String.format("You took on average %.1f seconds to answer correctly.\n" + 
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
			meanSolutionAge *= 86400;
			//meanSolutionAgeUnits = "d";
			oldestSolution = "never";
		}
		
		if (endTime > 0) {
			meanSolutionAge = endTime - new Double((1.0 + ages[0] + ages[1]) /2.0).intValue();
			int iOldestSolution = endTime - ages[0];
			int iOldestSolution2 = endTime - ages[1];
			oldestSolution = QzFSUtils.secondsToHumanTime(Math.max(iOldestSolution, iOldestSolution2), REMOVE_DECIMAL);
			meanSolutionTime = 1.0 * totalRating / nCorrect;
		}

		// TODO saying this question was posed at endTime is terribly inaccurate
		// it will fail with further testing.
		// maybe start time plus sum of previous response times would be better?
		int timeOfResponse = endTime;
		return processLine(QzFSUtils.alphagram(answers[0]), answers[0], timeOfResponse, responses[0], 1, ages[0], ratings[0], netResponseTimes[0]) +
		processLine(QzFSUtils.alphagram(answers[1]), answers[1], timeOfResponse, responses[1], 2, ages[1], ratings[1], netResponseTimes[1]) +
		getSummaryHeader(nCorrect, nBugTotal, elapsedSeconds, sYourAverageCongrats) + 
		getStats(nNoBugTotal, nCorrect,
				showMeanSolutionTime, meanSolutionTime,
				showMeanDifficulty, meanDifficulty,
				meanSolutionAge, oldestSolution);
	}
	

	private String getStats(int nNoBugTotal, int solved, 
			boolean showMeanSolutionTime, double meanSolutionTime, 
			boolean showMeanDifficulty, double meanDifficulty,
			int meanSolutionAge, String oldestSolution) {

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
		  "Mean solution time: %s\n" + 
		  "",
		  QzFSUtils.secondsToHumanTime(meanSolutionTime));
		String statsBaseMD =
		  String.format(
		  "Mean difficulty: %s\n" + 
		  "",
		  QzFSUtils.secondsToHumanTime(meanDifficulty));
		String statsBaseMSA =
		  String.format(
		  "Mean solution age: %s\n" + 
		  "",
		  QzFSUtils.secondsToHumanTime(meanSolutionAge, REMOVE_DECIMAL));
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
				QzFSUtils.durationToString(elapsedSeconds));
		if (nCorrect == 1) {
			s3 = s3.replace("questions correctly", "question correctly");
		}
		return s3;
	}
	
	/*
	[1] AQT: 
	The correct answer is 'QAT'  (100-100)
	*/
	private String processLine(String question, String answer, int timeOfResponse, String response, int questionNumber, int oldAge, int oldRating, double timeToSolve) {
		boolean bRight = response.toUpperCase().equals(answer.toUpperCase());
		int newRating = evaluateRating(bRight, oldRating, timeToSolve);
		String sRight =
		  showQuestion(question, questionNumber) +
		  response +
		  "\n" + 
		  showIfCorrectAndModifyRating(bRight, answer, timeOfResponse, oldAge, oldRating, newRating);
		String sWrong = 
		  showQuestion(question, questionNumber) +
		  "\n" +
		  showIfCorrectAndModifyRating(bRight, answer, timeOfResponse, oldAge, oldRating, newRating);
		if (bRight) {
			return sRight;
		}
		return sWrong;
	}

	/*
	The correct answer is 'QAT'  (100-100)
	*/
	private String showIfCorrectAndModifyRating(boolean correct, String answer, int timeOfAnswer, int oldAge, int oldRating, int newRating) {
		if (correct) {
			String sAgeStuff = "never";
			if (oldAge > 0) {
				sAgeStuff = QzFSUtils.secondsToHumanTime(timeOfAnswer - oldAge, REMOVE_DECIMAL);
			}
			return String.format("Correct.  (%s:%d-%d)\n", sAgeStuff, oldRating, newRating);
		}
		return String.format("The correct answer is '%s'  (%d-%d)\n", answer, oldRating, newRating);
	}
	

