package myqz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class QzToJava {
	
	private static boolean INTERACTIVE = false;
	
	private static final int HARDWIRED_FIXME_START_TIME = 1503615666;

	private static final String DEFAULT_FILE_NAME = "mystuff.qz";
	
	private Double [] randies = {0.421638355664076, 0.791541593472601, 0.720717027123225, 0.258002589081066, 0.0163123033826942, 0.459587882723994, 0.302980809376404, 0.120917671633912, 0.116435847702096, 0.302799414902211, 0.883058948458523, 0.428700298061337, 0.805863290822334, 0.322573840699775, 0.382488279676632, 0.424911280250051, 0.754484053672801, 0.268348800599625, 0.361743731721116, 0.924972307476317};
	private int randyCounter = 0;
	private int fakeTimeCounter = HARDWIRED_FIXME_START_TIME;
	
	private int configMaxRating = 100;               // maximum difficulty rating in seconds
	private int configMri = 15;                      // minimum interval between repeats
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
	
	List<QzQuestion> errors = new ArrayList<QzQuestion>();
	List<Integer> fileNumDirty = new ArrayList<Integer>();
	List<String> fileNumFileName = new ArrayList<String>();
	List<Integer> fileNumQuestionCount = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		INTERACTIVE = true;
		QzToJava qtj = new QzToJava();
		
		qtj.fileData = "AQT	QAT	20	1503667973	CO	\n" + 
				"IQS	QIS	13	1503404498	CO	\n" + 
				"AEU	EAU	13	1503321694	CO	\n" + 
				"AQU	QUA	31	1503292841	CO	\n" + 
				"ADIQ	QADI QAID	22	1503314490	CO	\n" + 
				"EJO	JOE	14	1503336101	CO	\n" + 
				"AIJ	AJI	22	1503314831	CO	\n" + 
				"EEZ	ZEE	16	1503748618	CO	\n" + 
				"CINQ	CINQ	15	1503473873	CO	\n" + 
				"AIRW	WAIR	45	1503860167	CO	\n" + 
				"DNOUV	VODUN	32	1503575584	CO	\n" + 
				"DEIOV	VIDEO	68	1503407134	CO	\n" + 
				"AOTU	AUTO OUTA	45	1503534861	CO	\n" + 
				"AJPU	JAUP PUJA	67	1503407113	CO	\n" + 
				"DGI	DIG GID	46	1503415319	CO	\n" + 
				"DFIN	FIND	45	1503407859	CO	\n" + 
				"BNU	BUN NUB	67	1503407124	CO	\n" + 
				"ADEX	AXED	45	1503408046	CO	\n" + 
				"KOY	YOK	32	1503417605	CO	\n" + 
				"CEITV	CIVET EVICT	47	1503860001	CO	\n" + 
				"EIIPRU	EURIPI	68	1503666280	CO	\n" + 
				"BILTZ	BLITZ	67	1503666042	CO	\n" + 
				"AGRUU	AUGUR	46	1503751808	CO	\n" + 
				"EOPRV	PERVO PROVE	48	1503836993	CO	\n" + 
				"AJMOR	JORAM MAJOR	50	1503673734	CO	\n" + 
				"AJLOP	JALOP	67	1503666231	CO	\n" + 
				"DEEFU	FEUED	67	1503666234	CO	\n" + 
				"EINNU	ENNUI	45	1503865379	CO	\n" + 
				"BEGIO	BOGIE	67	1503666272	CO	\n" + 
				"AEFRW	WAFER	70	1504016600	CO	\n" + 
				"AELNV	NAVEL VENAL	100	0	CO	\n" + 
				"HINNY	HINNY	100	0	CO	\n" + 
				"AGHIZ	GHAZI	67	1504016946	CO	\n" + 
				"AEIUVX	EXUVIA	100	0	CO	\n" + 
				"EENOVZ	EVZONE	69	1504017031	CO	\n" + 
				"EELTU	ELUTE	100	0	CO	\n" + 
				"AIKUZ	AZUKI	69	1504016798	CO	\n" + 
				"BCEEZ	ZEBEC	68	1504016802	CO	\n" + 
				"EENWY	WEENY	67	1504016711	CO	\n" + 
				"";
		
		
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
		
		initializeBeforeAsk();
		
		@SuppressWarnings("unused")
		int promptQord = 0;
		int tries = 0;
		
		this.fakeTimeCounter = QzToJava.HARDWIRED_FIXME_START_TIME + 10;
		while(tries < 20) {

		   // pick an algorithm
		    int algorithm = 0;
		    {
		      double t = myRand(1);
		      while (t>0){ 
		    	  t -= algProbs.get(algorithm++);
		      }
		      algorithm--;
		    }
		    
		    // lvb check to stop test cases from never ending
		    if (this.promptQOrd > 100) {
		    	System.out.println("!!!!!!!!! infinite loop ending at 100");
		    	System.exit(1);
		    }

		    // ask the question
		    int rc;
		    
		    System.out.println(algorithm);

		    switch(algorithm) {
		    	case 0:
		    		rc = askInOrder(questionsByRating);
		    		break;
		    	case 1:
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

	    System.out.print(promptText);

	    if (INTERACTIVE) {
			String answer = quest.answer;
			String read = readLine();

			if (! read.equals(answer)) {
				return giveUp(quest);
			}
		}
		else {
			System.out.println();
			if (this.promptQOrd % 3 == 0) { return giveUp(quest); };
		}

		
		time = 2;
		gotIt(quest, time);
		return 0;
	}
	
    // I don't use scanner coz it doesn't work in IDE
	private String readLine() {
		String sRet = "syn";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			sRet = br.readLine();
			//br.close();
		} catch (IOException exc) {
			System.out.println("cot:" + exc);
		}
		return sRet.toUpperCase();
	}
	
	private int giveUp(QzQuestion quest) {
		System.out.println("The correct answer is " + quest.answer);
		this.errors.add(quest);
		this.setRating(quest, this.configMaxRating);
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
			touchQ(quest);
		}
		int diff = newRating - oldRating;
	    totalRating += diff;
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
		    int q = 0;
		    QzQuestion quest = questions.get(q);
		    if (promptQOrd - quest.whenAsked >= configMri) {
		      // print "$q: ";
		      int result = askQ(quest);
		      removeQuestionFromLists(quest);
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
		if (errors.size() > 0 && this.promptQOrd - errors.get(0).whenAsked >= this.configMri) {
			QzQuestion quest = errors.remove(0);
			System.out.println("!!!! review ");
			int ret = askQ(quest);
			if (ret == 0) {
				return 0;
			}
			else {
				return 2;
			}
		}
		return 1;
	}

	private int askInOrder(List<QzQuestion> qList) {
		for (QzQuestion quest: qList) {
			if (promptQOrd - quest.getWhenAsked() < configMri) {
			    	continue;
			}
			int result = askQ(quest);
			removeQuestionFromLists(quest);

			if (result == 0) { return 0; } // asked
			if (result == 1) { return 2; } // EOF
		}
		return 1;
	}

	private void removeQuestionFromLists(QzQuestion quest) {
		// lvb added these 2
		questionsByAge.remove(quest);		
		questionsByRating.remove(quest);
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
		
		totalRating = createRatingTreeReturningTotalRating();

		sortQuestionsByAge();		
		sortQuestionsByRating();
		
		// populate list with a -15 for each question
		for (int i = 0; i < this.qCount; i++) {
			questions.get(i).setWhenAsked(new Integer(-configMri));
		}
		System.out.println("munge done");
	}

	private int createRatingTreeReturningTotalRating() {
		int ret = 0;
		for (QzQuestion quest: questions) {
			ret += quest.rating;
		}
		return ret;
	}

	private void sortQuestionsByRating() {
		// qByRating list sort questions
		questionsByRating = new ArrayList<>(questions);
		Collections.sort(questionsByRating, Comparator.comparingInt(QzQuestion::getRating).reversed());
	}

	private void sortQuestionsByAge() {
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
	}

	public void loadData() {
		// TODO handle multiple files
		// String[] files = {DEFAULT_FILE_NAME};
		this.readFile(DEFAULT_FILE_NAME);

		this.qCount = questions.size();
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
