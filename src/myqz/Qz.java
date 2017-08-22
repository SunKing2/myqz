package myqz;

import java.io.*;
import java.util.*;

public class Qz {

  private static final int kFQuestion = 0;
  private static final int kFAnswer = 1;
  private static final int kFRating = 2;
  private static final int kFAge = 3;
  private static final int kFFlags = 4;
  private static final int kFNote = 5;
  private static final int kFFields = 6;


  //@config'default_fields_2 = ('+100', 0, 'C', ''); // fields to add if user has hand-edited a line containing just question and answer into file
  private int configMaxRating = 100; // maximum difficulty rating in seconds
  private int configMri = 15; // minimum interval between repeats
  private String configNotes = "notes.txt"; // external notes database
  //config::question_filter = sub { 1 }; // pattern questions must match
  //config::answer_filter = sub { 1 }; // pattern answer must match
  private String configProgram = "jjc"; // default program
  private int configTypingSpeed = 9; // user typing speed in characters per second



  private String[] gErrors = new String[0];
  private String[] gFileNumDirty = new String[0];
  private String[] gFileNumFileName = new String[0];
  private String[] gFileNumQuestionCount = new String[0];
  private String[] gQData = new String[0];
  private String[] gQByAge = new String[0];
  private String[] gQByRating = new String[0];

  private int      gQCount = 0;
  private int      gQCount2 = 0;
  private int      gQCorrect = 0;
  private String   gNotes = "";
  private long     gSessionStart = 0;

  private String[] gRatingTree  = new String[99];

  private int promptLastq = 0;        // last question asked
  private String  promptNote = "";    // text of note associated with current question
  private int promptQord = 0;         // 1-based ordinal number of current question 
  private String promptText = "";     // prompt text
  private int promptResumed = 0;      // set whenever we return from a TSTP
  private long promptStart = 0;       // time when current question was asked
  private int promptWaiting = 0;      // true if a prompt needs redisplaying after a TSTP

  private int gTotalRating = 0;
  private long gTotalTime = 0;

  private String[] gWhenAsked = new String[99];



  public static void main(String[] args) throws Exception {
    Qz qz = new Qz();
    qz.doRunQuiz(args);
    System.out.println("all done.");
  }

  private void doRunQuiz(String[] args) throws Exception {
    gSessionStart = System.currentTimeMillis() / 1000;

    // read notes file into gNotes

    if (args.length == 0) {
      args = getQzFilesAsArray();
    }
    loadData(args);
    mungeData();
    
    gQCorrect = 0;
    promptQord = 0;

    gTotalTime = 0;
    int tries = 0;
    gSessionStart = System.currentTimeMillis()/1000;
    
    while(true) {
    	int algorithm = pickAnAlgorithm();
        int rc = askHardest();

        if (rc == 1) {
        	if (algorithm <= 1) {
        		System.out.println("No more questions available.");
        		break;
        	}
        	else {
        		tries++;
        		continue;
        	}
        }
        else if (rc == 2) {
        	break;
        }
        tries = 0;
        
        //TODO check if tries >= 20  break 
    }
    saveData();
    promptQord--;
  }
  
  private void saveData() {
	  
  }
  
  private int askHardest() {
	  int rating = gQByRating.length;
	  
	  // seemingly go thru each element of array in reverse order
	  // seemingly looking for non blank elements
	  for (rating = gQByRating.length; rating >= 0; rating--){
		  if (gQByRating[rating].length() < 1) {
			  continue;
		  }
		  // seemingly this element is not blank
		  // seemingly contains a string such as "0 1 2 " (sic)
		  for (String q:  gQByRating[rating].split(" ")) {
			  int result = askQ(q);
		      if (result == 0) { return 0; } // asked
		      if (result == 1) { return 2; } // EOF
		  }
	  }
	  
	  return 1;
  }
  
  private int askQ(String q) {
	  return -1;
  }

  private int pickAnAlgorithm() {
	  return 0;
  }
  private String[] getQzFilesAsArray() {
    String[] qzFiles = new String[1];
    qzFiles[0] = "mystuff.qz";
    return qzFiles;
  }

  private void loadData(String[] files) throws Exception {
    int i = 0; // i is the index of files (first file in array is 0)
    int iq = 0;

    Scanner x = new Scanner(new File(files[i]));
    int fileQCount = 0;
    while (x.hasNextLine()) {
      String line = x.nextLine();
      if (line.trim().isEmpty()) continue; // skip blank lines.
      String[] parts = line.split("\t", 6);
      // add all parts array to gQData array
      for (String sPart: parts) {
    	  gQData = push(gQData, sPart);
      }
      fileQCount++;
      output("fileQCount", fileQCount);
      gQCount++;
      output("gQCount", gQCount);
    }
    x.close();
    outputStringArray("gQData", gQData);
    gFileNumDirty = push(gFileNumDirty, "0");
    outputStringArray("gFileNumDirty", gFileNumDirty);
    gFileNumFileName = push(gFileNumFileName, files[i]);
    outputStringArray("gFileNumFileName", gFileNumFileName);
    gFileNumQuestionCount = push(gFileNumQuestionCount, "" + fileQCount);
    outputStringArray("gFileNumQuestionCount", gFileNumQuestionCount);
  }
  
  static <T> T[] push(T[] arr, T element) {
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
  }
  
  private void mungeData() {
    for (gQCount2 = 1; gQCount2 < gQCount; gQCount2 <<= 1) { }
    output("gQCount2", gQCount2);
    
    // create and fill array of 102 for example
    gQByRating = new String[configMaxRating + 2];
    for (int i = 0; i < configMaxRating + 2; i++) {
    	gQByRating[i] = new String();
    }
    outputStringArray("gQByRating", gQByRating);

    gTotalRating = 0;
    {
      int q = 0;
      int qIndex = kFRating;
    }
    
    // data from qz.pl debug session:
    /*
    gQCorrect = 0;
    gQCount = 3;
    gQCount2 = 4;
    gTotalRating = 300;
    gFileNumDirty = {"0"};
    gFileNumQuestionCount = {"3"};
    gQByAge = {"0", "2", "1"};
    gRatingTree = {"100", "200", "100"};
    gWhenAsked = {"-15", "-15", "-15"};
     */

    // the rest of this method is fake data
    // I got from running a debug session on the perl original
    gQByRating[100] = "0 1 2 ";
    gTotalRating = 300;
    gQByAge = new String[]{"0", "2", "1"};
    gRatingTree = new String[]{"100", "200", "100"};
    gWhenAsked = new String[]{"-15", "-15", "-15"};
    System.out.println("munge method done.");
  }

  private int parseInt(String s) {
    int iReturn = 0;
    try {
      iReturn = Integer.parseInt(s);
    }
    catch (Exception exc) {
      System.out.println(exc);
    }
    return iReturn;
  }

  private void outputStringArray(String arrayName, String[] array) {
    System.out.println("Outputting:" + arrayName);
    int i = 0;
    for (String element: array) {
      System.out.println("" + i++ + ' ' + element);
    }
  }
  private void output(String objectName, Object obj) {
    System.out.println("Output object:" + objectName + ':' + obj);
  }

}

