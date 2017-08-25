package myqz;

import java.util.*;

public class QzToJava {
	
	private static final int HARDWIRED_FIXME_START_TIME = 1503615666;

	private static final String DEFAULT_FILE_NAME = "mystuff.qz";
	
	
	//@config'default_fields_2 = ('+100', 0, 'C', ''); # fields to add if user has hand-edited a line containing just question and answer into file
	private int configMaxRating = 100;               // maximum difficulty rating in seconds
	private int configMri = 15;                      // minimum interval between repeats
	// $configNotes = 'notes.txt'; # external notes database
	// $config::question_filter = sub { 1 }; # pattern questions must match
	// $config::answer_filter = sub { 1 }; # pattern answer must match
	private String configProgram = "jjc";            // default program
	private int configTypingSpeed = 9;               // user typing speed in characters per second

	// TODO make this work
	/*
	%config'programs = (
	  'hardest', '100;0;0;0',
	  'oldest',  '0;100;0;0',
	  'random',  '0;0;100;0',
	  'jjc',     '30;20;40;10',
	  );
	*/
	

	private String fileData = 
			"GUV	GUV VUG	69	1503409287	CO	\n" + 
			"DIOOT	OOTID	30	1503495163	CO	\n" + 
			"MNOU	MUON	45	1503587550	CO	\n" + 
			"HQRSU	QURSH	68	1503606581	CO	\n" + 
			"NSY	SYN	100	0	CO	\n" + 
			"AFLN	FLAN	100	0	CO	\n" + 
			"DEEF	FEED	100	0	CO	\n" + 
			"AAFNU	FAUNA	100	0	CO	";

	private List<QzQuestion> questions = new ArrayList<QzQuestion>();
	private List<QzQuestion> questionsByAge = new ArrayList<>(questions);

	// package because test cases need to access them
	int qCorrect;
	int qCount;
	int qCount2;
	int sessionStart;
	int totalRating;
	int totalTime;
	
	List<String> errors = new ArrayList<String>();
	List<Integer> fileNumDirty = new ArrayList<Integer>();
	List<String> fileNumFileName = new ArrayList<String>();
	List<Integer> fileNumQuestionCount = new ArrayList<Integer>();
	Set<Integer> qByAge = new LinkedHashSet<Integer>();
	List<String> qByRating = new ArrayList<String>();//String!!
	//private List<String> qData = new ArrayList<String>();
	//private List<String> randies = new ArrayList<String>();
	List<Integer> ratingTree = new ArrayList<Integer>();
	List<Integer> whenAsked = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		QzToJava qtj = new QzToJava();
		qtj.doRunQuiz(args);
	}

	// TODO return a system time (probably millis/1000
	private int time() {
		return HARDWIRED_FIXME_START_TIME;
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
		
		//create algProbs List
		List<Double> algProbs = normalizeAlgorithmDistribution();
		

		// TODO why reinitialize this here?
		this.qCorrect = 0; 
		int promptQord = 0;
		this.totalTime = 0;
		int tries = 0;
		
		sessionStart = time();
		
		while(true) {

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
		    //= &{(\&AskHardest,\&AskOldest,\&AskRandom,\&AskReview)[$algorithm]};
		    switch(algorithm) {
		    	case 0:
		    		rc = askHardest();
		    		break;
		    	case 1:
		    		rc = askOldest();
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
		promptQord--;
	}

	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	private int askRandom() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int askReview() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int askOldest() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int askHardest() {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO implement this properly
	private List<Double> normalizeAlgorithmDistribution() {
		List<Double> lis = new ArrayList<Double>();
		lis.add(.3);
		lis.add(.2);
		lis.add(.4);
		lis.add(.1);
		return lis;
	}

	private void louieInitialize() {
		this.qCorrect = 0;
		this.sessionStart = 1503615666;
		//this.totalRating = 612;
		this.totalTime = 0;
	}

	private Double myRand(int multiplier) {
		return Math.random() * multiplier;
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

		// qByAge list
		// lone of questions, sort it
		questionsByAge = new ArrayList<>(questions);
		Collections.sort(questionsByAge, Comparator.comparingInt(QzQuestion::getAge));
		
		// populate list with a -15 for each question
		for (int i = 0; i < this.qCount; i++) {
			whenAsked.add(new Integer(-configMri));
		}
		System.out.println("stop here");
	}

	private int createRatingTreeReturningTotalRating() {
		// TODO Auto-generated method stub
		int tot = 0;
		int iQuestion = 0;
		for (QzQuestion q: questions) {
			tot += q.rating;
			String s = this.qByRating.get(q.rating);
			System.out.println("existing:" + s);
			this.qByRating.set(q.rating, "" + s + iQuestion + ' ');
			iQuestion++;
		}
		for (int i = 0; i < qByRating.size(); i++) {
			System.out.println("qbr:" + i + ": [" + qByRating.get(i) + "]");
		}
		return tot;
	}

	public void loadData() {
		String[] files = {DEFAULT_FILE_NAME};
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
			for (String sField: fields) {
				System.out.println("..." + sField);
			}
			questions.add(new QzQuestion(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), fields[4]));
			iFileNumQuestions++;
		}
		for (QzQuestion q: questions) {
			System.out.println(q.question);
			System.out.println(q.answer);
			System.out.println(q.rating);
			System.out.println(q.age);
			System.out.println(q.flags);
			
		}
		this.fileNumDirty.add(0); // first file not written to yet
		this.fileNumFileName.add(fileName);
		this.fileNumQuestionCount.add(iFileNumQuestions);
	}
}
