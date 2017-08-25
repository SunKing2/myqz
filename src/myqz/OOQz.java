package myqz;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OOQz {
	
	private List<Question> questions = new ArrayList<Question>();
	
	// static block gets called when class is created:
	{
		questions.add(new Question("ABT", "BAT", 20));
		questions.add(new Question("ATU", "EAU", 40));
	}

	public List<Question> readFile(String fileName) {
		List<Question> lis = new ArrayList<Question>();
	    Scanner scanner;
	    try {
			scanner = new Scanner(new FileReader(fileName));
		    scanner.useDelimiter("\\t");
		    
		    while(scanner.hasNextLine()) {
	    		String line = scanner.nextLine();
	    		String[] fs = line.split("\t");
	    		lis.add(new Question(fs[0], fs[1], Integer.parseInt(fs[2])));
		    }
		    scanner.close();
	    }
	    catch (Exception exc) {
	    	System.out.println("exc:" + exc);
	    }
	    this.questions = lis;
	    return lis;
	}

	public void writeFile(String fileName) {
		String text = OOQzUtils.questionsToString(this.questions);
		try {
			Files.write(Paths.get(fileName), text.getBytes());
		} catch (IOException exc) {
			System.out.println("writeFile exc:" + exc);
		}		
	}

	public List<Question> getQuestions() {
		return questions;
	}
}
