package myqz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class QzToJava {
	
	private static final int HARDWIRED_FIXME_START_TIME = 1503615666;

	private static final String DEFAULT_FILE_NAME = "mystuff.qz";
	
	private Double [] randies = {0.421638355664076, 0.791541593472601, 0.720717027123225, 0.258002589081066, 0.0163123033826942, 0.459587882723994, 0.302980809376404, 0.120917671633912, 0.116435847702096, 0.302799414902211, 0.883058948458523, 0.428700298061337, 0.805863290822334, 0.322573840699775, 0.382488279676632, 0.424911280250051, 0.754484053672801, 0.268348800599625, 0.361743731721116, 0.924972307476317};
	private int randyCounter = 0;
	private int fakeTimeCounter = HARDWIRED_FIXME_START_TIME;
	
	private int configMaxRating = 100;               // maximum difficulty rating in seconds
	private int configMri = 15;                      // minimum interval between repeats
	// $configNotes = 'notes.txt'; # external notes database
	// $config::question_filter = sub { 1 }; # pattern questions must match
	// $config::answer_filter = sub { 1 }; # pattern answer must match
	// private String configProgram = "jjc";            // default program
	// private int configTypingSpeed = 9;               // user typing speed in characters per second

	// TODO make this work
	/*
	%config'programs = (
	  'hardest', '100;0;0;0',
	  'oldest',  '0;100;0;0',
	  'random',  '0;0;100;0',
	  'jjc',     '30;20;40;10',
	  );
	*/
	List<Double> algProbs = new ArrayList<Double>(Arrays.asList(0.3, 0.2, 0.4, 0.1));
	

	private String fileData = 
			"GUV	GUV VUG	69	1503409287	CO	\n" + 
			"DIOOT	OOTID	30	1503495163	CO	\n" + 
			"MNOU	MUON	45	1503587550	CO	\n" + 
			"HQRSU	QURSH	68	1503606581	CO	\n" + 
			"NSY	SYN	100	0	CO	\n" + 
			"AFLN	FLAN	100	0	CO	\n" + 
			"DEEF	FEED	100	0	CO	\n" + 
			"AAFNU	FAUNA	100	0	CO	";

	// package because test cases need to access them
	List<QzQuestion> questions = new ArrayList<QzQuestion>();
	private List<QzQuestion> questionsByAge = new ArrayList<>(questions);
	private List<QzQuestion> questionsByRating = new ArrayList<>(questions);

	// package because test cases need to access them
	int qCorrect;
	int qCount;
	int qCount2;
	int sessionStart;
	int totalRating;
	int totalTime;
	
	private int promptQOrd = 0;
	
	List<String> errors = new ArrayList<String>();
	List<Integer> fileNumDirty = new ArrayList<Integer>();
	List<String> fileNumFileName = new ArrayList<String>();
	List<Integer> fileNumQuestionCount = new ArrayList<Integer>();
	List<String> qByRating = new ArrayList<String>();//String!!
	List<Integer> ratingTree = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		QzToJava qtj = new QzToJava();
		qtj.setAlgorithm(0.0, 0.0, 1.0, 0.0);
		qtj.doRunQuiz(args);
	}

	// TODO return a system time (probably millis/1000
	private int time() {
		return this.fakeTimeCounter++;
	}
	
	// typically *.qz
	// or even myfile.qz
	public void doRunQuiz(String[] args) {

		// TODO is setting time variable really needed?  because we update it
		// again in this method
		sessionStart = time();

		loadData();
		if (qCount <= 0) {
			System.out.println ("There are no questions in the files  you specified.");
			System.exit(0);
		}
		mungeData();
		
		System.out.println("rt3 is " + ratingTree.get(3));
		
		//create algProbs List
		//List<Double> algProbs = normalizeAlgorithmDistribution();
		

		initializeBeforeAsk();
		
		@SuppressWarnings("unused")
		int promptQord = 0;
		int tries = 0;
		
		this.fakeTimeCounter = QzToJava.HARDWIRED_FIXME_START_TIME + 10;
		while(tries < 20) {

		   // pick an algorithm
		    int algorithm = 0;
		    {
		      Double t = myRand(1);
		      while (t>0){ 
		    	  t -= algProbs.get(algorithm++);
		      }
		      algorithm--;
		    }

		    // ask the question
		    int rc;
		    System.out.println("algorithm:" + algorithm);
			System.out.println("rt3 is " + ratingTree.get(3));

		    switch(algorithm) {
		    	case 0:
		    		rc = askInOrder(questionsByRating);
		    		break;
		    	case 1:
		    		System.out.println("qba 0:" + questionsByAge.get(0).question);
		    		rc = askInOrder(questionsByAge);
		    		break;
		    	case 3:
		    		rc = askReview();
		    		break;
		    	default:
		    		rc = askRandom();
		    }
		    if (rc == 1) { 
		      if (algorithm <=1 ){ 
		    	  System.out.println("No more questions available.");
		    	  break;
		      }
		      else { tries++; continue; }
		      }
		    else if (rc == 2) { break; }
		    tries = 0;
		}
		saveData();
		System.out.println(this.getQuestionsAsString());
		promptQord--;
	}

	
	/* 
	# returns 0 if question was asked 
	# returns 1 if user signalled end of file
	# returns 2 if question did not match filter and could not be asked
	*/
	int askQ(QzQuestion quest) {
		
		int time = 0;
		
		++promptQOrd;
		String promptText  = String.format("[%d] %s: ", promptQOrd, quest.getQuestion());
		
		quest.setWhenAsked(this.promptQOrd);
		
	    System.out.println(promptText);
		if (this.promptQOrd % 3 == 0) { return giveUp(quest); };

		//time = time();
		// readLine returning $time, and $read
		// answer = questions.get(q).answer;
		// read = readLine(time, bLowerCase);
		//if (! read.equals(answer)) {
		//	return giveUp();
		//}
		
		time = 2;
		gotIt(quest, time);
		return 0;
	}
	
	private int giveUp(QzQuestion quest) {
		System.out.println("The correct answer is " + quest.answer);
		//    . "  (" . (FormatRating $gQData[$q*$k'fields+$k'fRating])
		//    . "-$config'max_rating)\n";
		this.errors.add("" + quest.getReadInOrder());
		this.setRating(quest, this.configMaxRating);
		//this.prompt'lastq = $q;
		//promptLastQ = q;
		return 0;
	}
	
	private void gotIt(QzQuestion quest, int time) {
		int oldAge = quest.age;
		int newAge = time();
		quest.age = newAge;
		
		int oldRating = quest.rating;
		int newRating = (int) ( (1.0 + 2.0 * oldRating + time ) / 3.0);
		
		
		totalTime += time;
		if (newRating < 1) {
			newRating = 1;
		}
		else {
			if (newRating > configMaxRating) {
				newRating = configMaxRating;
			}
		}
		// this simulates the call to time that the perl program does
		// when doing a format with "correct" print statement
		if (oldAge != 0) {
			time();
		}
	    System.out.println( "Correct.  (" + oldRating + ' ' + newRating + ' ' + oldAge + ' ' + newAge + ')');
	    qCorrect++;
		
		setRating(quest, newRating);
		
		touchQ(quest);
		//promptLastQ = q;
	}
	
	private void setRating(QzQuestion quest, int newRating) {
		int qNumber = quest.readInOrder;
		int oldRating = quest.rating;
		if (newRating != oldRating) {
			quest.rating = newRating;
			System.out.println("jTouching:" + qNumber);
			touchQ(quest);
		}
		int diff = newRating - oldRating;
	    totalRating += diff;
		while (qNumber < qCount) { // ELFS: prove that this works
		      ratingTree.set(qNumber, ratingTree.get(qNumber) + diff);
		      qNumber |= qNumber+1;
		}
	}
	
	private void touchQ(QzQuestion quest) {
		
		int fileNumber = 0;
		int qNumber = quest.readInOrder;

		  if (qNumber == 0) { fileNumber = 1; } // exceptional case: no questions
		  else {
		    int q = 0;
		    while (q <= qNumber) {
		    	
		       
			  if (fileNumber > fileNumQuestionCount.size()) {
				  System.out.println("died in touch:" + qNumber + " " + fileNumber + " " +  fileNumQuestionCount );
				  System.exit(1);
			  }
		      //q += fileNumQuestionCount.get(fileNumber++); 
			  // TODO  don't hard code this file number
		      q += fileNumQuestionCount.get(0); 
		      }
		    }
		  // TODO  don't hard code this file number
		  // fileNumDirty.set(fileNumber, 1);
		  fileNumDirty.set(0, 1);
	}

	// package visibility due to test case
	void initializeBeforeAsk() {
		
		// TODO why reinitialize all 3 of these here?
		sessionStart = time();
		this.qCorrect = 0; 
		this.totalTime = 0;
	}

	// TODO doesn't seem to be adding EOL to saved file
	public void saveData() {
		System.out.println("saving myout.qz");
		String text = getQuestionsAsString();
		String fileName = "myout.qz";
		try {
			Files.write(Paths.get(fileName), text.getBytes());
		} catch (IOException exc) {
			System.out.println("writeFile exc:" + exc);
		}				
	}

	private String getQuestionsAsString() {
		List<Question> lis = new ArrayList<Question>(this.questions);
		String text = QzUtils.questionsToString(lis);
		return text;
	}

	private int askRandom() {


		  for (int i = 1; i <= 20; i++) {
		    double rand = myRand(totalRating);
		    System.out.println("===" + " i=" + i + " totalRating=" + totalRating + " rand=" + rand);
		    int q = 0;
		    int width = qCount2;
		    while (width > 1) {
		      width >>= 1;
		      int mid = q + width;
		      if (i == 4 && promptQOrd == 6) {
		        	//print "xxx width $width tree $gRatingTree[$mid-1]\n";
		    	    System.out.println("jjj width " + width + " mid-1 " + (mid-1)  + " tree " + ratingTree.get(mid-1)  );
		        }

		      if (mid <= qCount && rand >= ratingTree.get(mid-1)) {
			    rand -= ratingTree.get(mid-1);
			    q = mid;
			  }
		    }
		    // "q:$q promptqord: $promptqord gWhenAsked: $gWhenAsked[$q]\n";
		    System.out.println("q:" + q + " promptqord:" + promptQOrd + " gWhenAsked:" + questions.get(q).whenAsked );

		    if (promptQOrd - questions.get(q).whenAsked >= configMri) {
		      // print "$q: ";
		      int result = askQ(questions.get(q));
		      if (result == 0) { return 0; } // asked
		      if (result == 1) { return 2; } // EOF
		    }
		    else {
		    	//  print "skipping $q\n";
		    }
		  }
		  return 1;
		  	
	
	}

	private int askReview() {
		return askInOrder(questionsByAge);
	}

	private int askInOrder(List<QzQuestion> qList) {
		for (QzQuestion quest: qList) {
			if (promptQOrd - quest.getWhenAsked() < configMri) {
			    	continue;
			}
			int result = askQ(quest);
			// even if give up (blank answer), still 0
			
			//System.out.println(this.getQuestionsAsString());
			
			if (result == 0) { return 0; } // asked
			if (result == 1) { return 2; } // EOF
		}
		return 1;
	}

	private Double myRand(int multiplier) {
		if (randyCounter > 19) {
			randyCounter = 0;
		}
		Double ret = randies[randyCounter++] * multiplier;
		//System.out.println("myRand returning:" + ret);
		return ret;
	}
	
	void mungeData() {
		// round up to a power of two
		for (qCount2 = 1; qCount2 < qCount; qCount2 <<= 1) { }
		System.out.println("qCount2=" + qCount2);
		
		// calculate rating information
		//@gQByRating = ('') x ($config'max_rating + 2);
		for (int i = 0; i < this.configMaxRating + 2; i++) {
			this.qByRating.add(new String(""));
		}
		
		totalRating = createRatingTreeReturningTotalRating();
		System.out.println("totalRating=" + totalRating);

		// qByAge list sort questions
		questionsByAge = new ArrayList<>(questions);
		//Collections.sort(questionsByAge, Comparator.comparingInt(QzQuestion::getAge));
	    Comparator<QzQuestion> ageComparator
	      = Comparator.comparing(
	        QzQuestion::getAge, (s1, s2) -> {
	        	if (s2.compareTo(s1) == 0) {
	        		//Double ran = myRand(3);
	        		//System.out.println("ran=" + ran);
	        		//int compareValue = ran.intValue()-1;
	        		//System.out.println("compareValue=" + compareValue);
	        		//return compareValue;
	        	}
	            return s1.compareTo(s2);
	        });
	    Collections.sort(questionsByAge, ageComparator);
		
		// qByRating list sort questions
		questionsByRating = new ArrayList<>(questions);
		Collections.sort(questionsByRating, Comparator.comparingInt(QzQuestion::getRating).reversed());
		
		// populate list with a -15 for each question
		for (int i = 0; i < this.qCount; i++) {
			questions.get(i).setWhenAsked(new Integer(-configMri));
		}
		System.out.println("munge done");
	}

	private int createRatingTreeReturningTotalRating() {
		
		// create ratingTree
		totalRating = 0;
		
		for (int q = 0; q < qCount; q++) {
			int rating = questions.get(q).rating;
			ratingTree.add(rating);
			totalRating += rating;
			if (q % 2 == 0) {
				continue;
			}
			int t1 = q - 1;
			//$gRatingTree[$q] += $gRatingTree[$t1];
			ratingTree.set(q, ratingTree.get(q) + ratingTree.get(t1));
		    int t2 = 1;
		    int t3 = q & (1 + q);
		    while ((t1-=t2) > t3) { 
		    	t2 += t2;
		    	ratingTree.set(q, ratingTree.get(q) + ratingTree.get(t1)); 
		    }
		}
		// end creating ratingTree
		
		int tot = 0;
		int iQuestion = 0;
		for (QzQuestion quest: questions) {
			tot += quest.rating;
			String s = this.qByRating.get(quest.rating);
			this.qByRating.set(quest.rating, "" + s + iQuestion + ' ');
			iQuestion++;
		}
		return tot;
	}

	public void loadData() {
		// TODO handle multiple files
		// String[] files = {DEFAULT_FILE_NAME};
		this.readFile(DEFAULT_FILE_NAME);

		this.qCount = questions.size();
		System.out.println("qCount=" + qCount);
	}

	private void readFile(String fileName) {
		int iFileNumQuestions = 0;
		
		String[] strings = fileData.split("\n");
		for (String sLine: strings) {
			System.out.println(sLine);
			
			String[] fields = sLine.split("\\t");
			QzQuestion qNew = new QzQuestion(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), fields[4]);
			qNew.setReadInOrder(iFileNumQuestions);
			iFileNumQuestions++;
			questions.add(qNew);
		}
		this.fileNumDirty.add(0); // first file not written to yet
		this.fileNumFileName.add(fileName);
		this.fileNumQuestionCount.add(iFileNumQuestions);
	}

	public void setAlgorithm(double d, double e, double f, double g) {
		this.algProbs = new ArrayList<Double>(Arrays.asList(d, e, f, g));
	}
}
