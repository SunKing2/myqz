package myqz2;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// A template is an array of String,
// typically, each ends with \n
// createTemplateUsingStringsArray() 
// addReplacement() to replace items in the template.
// getResults() performs substitutions in the template with real values,
//    and returns a single string. (it concatenates the substituted array). 

// All substitutions use String only, there are no numbers or other data types

// Typical use:
/*
	String[] myTemplate = 
	{
		  "The question ${question1} is incorrect.\n", 
		  "You answered ${response1}\n", 
		  "The correct response is ${answer1}"
	};
	TemplateEngine engine = new TemplateEngine();
	
	engine.createTemplateUsingStringsArray(myTemplate);
	
	engine.addReplacement("question1", "What is the answer to life, the universe and everything?");
	engine.addReplacement("response1", "a bowl of cherries");
	engine.addReplacement("answer1", "42");
	
	System.out.println(engine.getResults());		

*/

public class TemplateEngine {

	private String[] originalStrings = null;
	private boolean[] showThisString = null;
	
	private Map<String, String> mapOfReplacements = new HashMap<>();
	
	public void createTemplateUsingStringsArray(String[] stringsToAdd) {
		int nAdded = stringsToAdd.length;
		
		// create array to indicate that each string should be shown
		showThisString = new boolean[nAdded];
		for (int i = 0; i < nAdded; i++) {
			showThisString[i] = true;
		}

		this.originalStrings = stringsToAdd;
	}
	
	public void addReplacement(String stringToReplace, String finalValue) {
		mapOfReplacements.put("${" + stringToReplace + "}", finalValue);
		//System.out.println("map=" + mapOfReplacements);
	}
	
	// this processes the template by substituting strings, 
	// returns the list of strings as one big long string concatenated
	public String getResults() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String sOriginal: originalStrings) {
			String s = replaceUsingMap(sOriginal, mapOfReplacements);
			if (showThisString[i]) {
				sb.append(s);
			}
			i++;
		}
		return sb.toString();
	}

	public void setStringVisibility(int stringNumber, boolean visible) {
		try {
			showThisString[stringNumber] = visible;
		}
		catch (Exception exc) {
			System.out.println("setStringVisibility not done:" + exc);
		}
	}

	// set a bunch of strings visible/invisible by passing 
	// an array of their string number
	// ex {3, 4, 10} passing false will hide string numbers 3, 4, 10
	public void setStringVisibilities(int[] visibilities, boolean visible) {
		for (int stringNumber: visibilities) {
			this.setStringVisibility(stringNumber, visible);
		}
	}

	/* Example:
		String[][] replacements = {
			{"question1", "What is the answer to life, the universe and everything?"},
			{"response1", "a bowl of cherries"},
			{"answer1", "42"},
		};
	 */
	public void addReplacements(String[][] replacements) {
		for (String[]s: replacements) {
			this.addReplacement(s[0], s[1]);
		}		
	}

	private String replaceUsingMap(String sOriginal, Map<String, String> map) {
		String sFinal = sOriginal;
		for (String key : map.keySet()) {
			String regex = Pattern.quote(key);
			String replacement = map.get(key);
			sFinal = sFinal.replaceAll(regex, replacement);
		}
		return sFinal;
	}
}
