package qz;

public class QzTemplate extends TemplateEngine {
	private String[] stringsToAdd = {
			"No more questions available.\n" , 
			"\n" , 
			"You answered ${answered} question${questionplural} correctly of ${totalm1}",
			" (${answeredpercent}%)",
			".\n" ,
			"You took on average ${youtookonaverage} seconds to answer correctly.\n",
			"Congratulations!\n",
			"Elapsed time: ${elapsedtime}\n" , 
			"\n" , 
			"Current statistics for this question set:\n" , 
			"Total: ${total}\n" , 
			"unseen:  ${unseen} ",
			"(${unseenpercent}%)\n" , 
			"Solved: ${solved} ", 
			"(${solvedpercent}%)\n" , 
			"Unsolved: ${unsolved} ", 
			"(${unsolvedpercent}%)\n" , 
			"Mean solution time: ${meansolutiontime}\n" , 
			"Mean difficulty: ${meandifficulty}\n" , 
			"Mean solution age: ${meansolutionage}\n" , 
			"Oldest solution: ${oldestsolution}\n" , 
			""
			};
	
	// this is a static block, all code gets executed during class instantiation:
	{
		createTemplateUsingStringsArray(stringsToAdd);

		String[][] replacements = {
				{"question1", "AQT"},
				{"response1", "qat"},
				{"answer1", "QAT"},
				{"question2", "IQS"},
				{"response2", "qis"},
				{"answer2", "QIS"},
				{"oldrating1", "100"},
				{"newrating1", "67"},
				{"oldrating2", "100"},
				{"newrating2", "68"},
				{"answered", "2"},
				{"questionplural", "s"},
				{"answeredpercent", "200.0"},
				{"totalm1", "1"},
				{"youtookonaverage", "-0.4"},
				{"elapsedtime", "0:00:00"},
				{"total", "2"},
				{"unseen", "2"},
				{"solved", "2"},
				{"solvedpercent", "100"},
				{"unsolved", "0"},
				{"unsolvedpercent", "0"},
				{"meansolutiontime", "67.5 s"},
				{"meandifficulty", "100.0 s"},
				{"meansolutionage", "2 s"},
				{"oldestsolution", "never"}	
			};
		addReplacements(replacements);
	}	
}
