package myqz;

import org.apache.commons.cli.*;

public class Qzj {


	//
	// configuration constants
	//

	//@config'default_fields_2 = ('+100', 0, 'C', ''); # fields to add if user has hand-edited a line containing just question and answer into file
	//$config'max_rating = 100; # maximum difficulty rating in seconds
	private int configMri = 15; // minimum interval between repeats
	//$config'notes = 'notes.txt'; # external notes database
	//$config::question_filter = sub { 1 }; # pattern questions must match
	//$config::answer_filter = sub { 1 }; # pattern answer must match
	//$config'program = 'jjc'; # default program
	//$config'typing_speed = 9; # user typing speed in characters per second
	private String[] configPrograms = {
	  "hardest", "100;0;0;0",
	  "oldest",  "0;100;0;0",
	  "random",  "0;0;100;0",
	  "jjc",     "30;20;40;10",
	};
	private String[] configProgramHelp = {
	  "hardest", "do all questions from hardest to easiest",
	  "oldest",  "do all questions from oldest to newest",
	  "random",  "pick at random, biased toward hard questions",
	  "jjc",     "20% oldest, 20% hardest, 50% random, 10% review",
	};

	
	
	private String[] gQData = {};

	// list of question numbers sorted chronologically by last correct answer
	private String [] gQByAge = {};

	// map difficulty rating to list of space-separated question numbers
	private String[] gQByRating = {};

	// number of questions
	private int gQCount = 0;

	// number of questions, rounded up to a power of two
	private int gQCount2 = 0;

	// number of questions answered correctly
	private int gQCorrect = 0;

	// notes to display after question is answered (question\tnote\n...)
	private String gNotes = "";

	// time at start of session (seconds since epoch)
	private int gSessionStart;
	

	// tricky data structure defined as follows:
	//
	// gRT(x) := $gRatingTree[$x]; r(x) := rating of question $x
	// gRT(0)=r(0); gRT(1)=r(0)+r(1); gRT(2)=r(2); gRT(3)=r(0)+r(1)+r(2)+r(3)
	// gRT(4)=r(4); gRT(5)=r(4)+r(5); gRT(6)=r(6); gRT(7)=r(0)+...+r(7)...
	// i.e. gRT(2k)=r(2k); gRT(4k+1)=r(4k)+r(4k+1); gRT(8k+3)=r(8k)+...r(8k+3)
	// and in general, gRT((2k+1)*2**n-1)=sum i (2k*2**n..(2k+1)*2**n-1) r(i)
	//
	// This gives us linear data structure initialization and log n searches
	// and data structure updates; a big improvement over the old linear search.
	//
	// Thanks to my brain for thinking of this.

	//my @gRatingTree = ();

	// prompt-related variables
	//$prompt'lastq = undef;# last question asked
	//$prompt'note = undef; # text of note associated with current question
	private int promptQord = 0;     // 1-based ordinal number of current question 
	//$prompt'text = '';    # prompt text
	//$prompt'resumed = 0;  # set whenever we return from a TSTP
	//$prompt'start = undef;# time when current question was asked
	//$prompt'waiting = 0;  # true if a prompt needs redisplaying after a TSTP

	// total of all difficulty ratings
	private int gTotalRating = 0;

	// total time spent answering questions
	private int gTotalTime = 0;

	// question number to when question was asked in this session
	private int[] gWhenAsked = {};

	
	
	private CommandLine parseCommandLine(String[] args) throws ParseException {
		CommandLine cmd;
		Options options = new Options();
		options.addOption("a", false, "add a question");
		options.addOption("d", false, "delete questions");
		options.addOption("A", false, "add questions");
		options.addOption("l", false, "list questions");
		options.addOption("L", false, "load data");
		CommandLineParser parser = new BasicParser();
		cmd = parser.parse(options, args);
		return cmd;
	}
	

	public int askHardest() {		for (int rating = gQByRating.length; rating >= 0; rating--) {
		  if (gQByRating[rating].length() == 0) continue;
		  for (String sq:  gQByRating[rating].split("/ /")) {
			int q = Integer.parseInt(sq);
		    if (promptQord - gWhenAsked[q] >= configMri) continue;
		    int result = askQ(q);
		    if (result == 0) { return 0; } // asked
		    if (result == 1) { return 2; } // EOF
		  }
		}
		return 1;
	}
	public int askOldest() {
		return -1;
	}
	public int askQ (int q) {
/*
	  die "Oops ($q >= $gQCount)\nAborting" if $q >= $gQCount;
	  my ($answer, $flags, $question);
	  {
	    my $qIndex = $q * $k'fields;
	    $flags        = $gQData[$qIndex+$k'fFlags];
	    $answer       = $gQData[$qIndex+$k'fAnswer];
	    $answer       = lc $answer if $flags =~ /C/;
	    $question     = $gQData[$qIndex+$k'fQuestion];
	    return 2 unless &$config::question_filter($question)
	      && &$config::answer_filter($answer);
	    $prompt'note  = $gNotes =~ /^\Q$question\E\t(.*)/m ? $1 : '';
	    $prompt'note  = length($prompt'note)
	      ? "$prompt'note\n$gQData[$qIndex+$k'fNote]" : $gQData[$qIndex+$k'fNote];
	    $prompt'note =~ s/^\n//;
	    $prompt'note =~ s/<br>|<p>/\n/g;
	    $prompt'text  = sprintf("[%d] %s: ", ++$prompt'qord, $question);
	  }
	
	  my $time = 0;
	
	  $gWhenAsked[$q] = $prompt'qord;
	
	  if ($flags =~ /O/) { # don't care about order
	    my (%answer, %found);
	    my $count = 0;
	    for my $word (split(/\s+/, $answer)) { $answer{$word} = 1; $count++; }
	    my $originalCount = $count;
	    my $isFirstQ = 1;
	    while ($count > 0) {
	      unless ($isFirstQ) {
	  print %found
	    ? "Enter another answer, or press return to see them all.\n"
	    : "Press return to see the correct answer, or try again.\n";
	  }
	      my $read = (ReadLine $time, ($flags =~ /C/));
	      defined $read ? chop $read : return 1;
	      $isFirstQ = 0;
	      ($read =~ /\S/) || return GiveUp $q;
	      for my $word (split(/\s+/, $read)) {
		$answer{$word} 
	          ? $found{$word}++ 
		    ? print("You have already entered `$word'.\n")
		    : $count--
		  : print("`$word' is not correct.\n");
		}
	      }
	    }
	  else { # not in unordered mode
	    my $read = ReadLine $time, ($flags =~ /C/); 
	    defined $read ? chop $read : return 1;
	    $read eq $answer || return GiveUp $q;
	    }
	  GotIt $q, $time;
	
*/
		return -1;
	}
	public int askRandom() {
		return -1;
	}
	public int askReview() {
		return -1;
	}
	public void doAddQuestion(String[] args) {
		
	}
	public void doAddQuestions(String[] args) {
		
	}
	public void doDeleteQuestion(String[] args) {
		
	}
	public void doListQuestions(String[] args) {
		
	}
	public void doListStats () {
		
	}
	// DoRunQuiz(args) 
	// process 'qz file.qz...' 
	// by running a quiz
	public void doRunQuiz(String[] args) {

		  // $SIG{'TSTP'} = \&HandleTSTP if $gEnvironment eq 'unix';
		  gSessionStart = time();

		  // load external notes if necessary
		  // TODO uncomment adding notes
		  /*
		  if (configNotes != null) {
		    EOpenFile(*NOTES, '<'.$config'notes);
		    local($/) = undef;
		    $gNotes = <NOTES>;
		    close(NOTES);
		  }
		  */

		  // load data
		  if (args.length == 0) args[0] = "*.qz";
		  loadData(args);
		  if (gQCount <= 0) { System.out.println("There are no questions in the files you specified."); System.exit(0); }
		  mungeData();
		  if (time() - gSessionStart > 2) 
		    { System.out.println("Press return to begin."); readLine(); }

		  // TODO lvb added the assignment, real assignment is commented
		  double [] algProbs = {0.3, 0.3, 0.4, 0.3};
		  // normalise algorithm distribution
		  //algProbs = split(/;/, $config'programs{$config'program}, 4);
		  //{
		  //  my $tS = 0;
		  //  for my $t (@algProbs) { $tS += $t; } 
		  //  for my $t (@algProbs) { $t /= $tS; }
		  //}

		  gQCorrect = 0;
		  promptQord = 0;

		  gTotalTime = 0;
		  int tries = 0;
		  gSessionStart = time();
		  while (tries < 20) {
		    // pick an algorithm
		    int algorithm = 0;
		    {
		      double t = rand();
		      while (t>0) { t -= algProbs[algorithm++]; }
		      algorithm--;
		    }

		    // ask the question
		    int rc = askHardestAskOldestAskRandomAskReview(algorithm);
		    if (rc == 1) { 
		      if (algorithm <=1 ) { System.out.println("No more questions available."); break; }
		      else { tries++; continue; }
		    }
		    else if (rc == 2) { break; }
		    tries = 0;
		  }
		   
		  if (tries >= 20) {
			  System.out.println("Giving up after $tries tries at picking a question.");
		  }
		     

		  saveData();
		  promptQord--;
		  s();
		
		
		
	}
	
	// lvb new method
	private int askHardestAskOldestAskRandomAskReview(int algorithm) {
		int rc = -1;
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
    	return rc;
	}

	// lvb new method
	private double rand() {
		return Math.random();
	}


	// lvb new method
	private void readLine() {
		// TODO Auto-generated method stub
		// $_ = <STDIN>;
	}

	// lvb new method
	private int time() {
		return (int)System.currentTimeMillis()/1000;
	}
	public void eOpenFile(Object somethingWeird) {
		
	}
	public void formatTime (Object somethingWeird) {
		
	}
	public void formatRating (int r) {
		// TODO uncomment me
		// $r =~ s/^\+.*/new/; $r; 
	}
	public void giveUp (int q) {
		
	}
	public void gotIt (int q, int time) {
		
	}
	public void handleTSTP (Object somethingWeird) {
		
	}
	public void joinPath (String[] somethingWeird) {
		
	}
	public void loadData (String[] files) {
		
	}
	public void mungeData () {
		
	}
	public void parseCommandLine () {
		
	}
	public void readLine (int totaltime, boolean makelc) {
		
	}
	public void s () {
		
	}
	public void saveData () {
		
	}
	public void setRating (int qNumber, int newRating) {
		
	}
	public void touchQ (int qNumber) {
		
	}
	public void usage () {
		
	}

	public static void main(String[] args) throws ParseException {
		// srand;  // not needed as random numbers are seeded
		Qzj qz = new Qzj();
		CommandLine cmd = qz.parseCommandLine(args);
		if (cmd.hasOption("a")) {
			qz.doAddQuestion(args);
		}
		else if (cmd.hasOption("d")) {
			qz.doDeleteQuestion(args);
		}
		else if (cmd.hasOption("A")) {
			qz.doAddQuestions(args);
		}
		else if (cmd.hasOption("l")) {
			qz.doListQuestions(args);
		}
		// To list stats about questions: qz -L file1.qz [file2.qz...]\n".
		else if (cmd.hasOption("L")) {
			qz.loadData(args);
			qz.mungeData();
			qz.doListStats();
		}
		else {
			qz.doRunQuiz(args);
		}
	}
}
