package myqz;
import java.util.*;

public class QzFSStats2 {

	private List<QzData> gQData = new ArrayList<>(); 

	private int now;
	
	public QzFSStats2(List<QzData> data, int timeOfStats) {
		this.gQData = data;
		this.now = timeOfStats;
	}
	
	// DoListStats - list file stats
	public String doListStats () {
	  StringBuilder sb = new StringBuilder("");

	  int solved = 0;
	  int totalRating = 0;
	  int unseen = 0;
	  int unsolved = 0;
	  long ageSum = 0;
	  //int now = QzFSUtils.time();
	  int count = 0;
	  int t = Integer.MAX_VALUE; // oldest

	  // calculate stats
	  {
	    for (int i = 0; i < gQData.size(); i++) {
	      int thisAge = gQData.get(i).age;
	      if (thisAge < t) t = thisAge;
	      ageSum = ageSum + 0L + thisAge;
	      count++;
	      int rating = gQData.get(i).rating;
	      if (gQData.get(i).unseen == true) { unseen++; }
	      else if (rating == 100) { unsolved++; }
	      else { solved++; totalRating += rating; }
	    } 
	  }
	  System.out.println(gQData.get(0).age);
	  System.out.println(gQData.get(1).age);
	  System.out.println(t);
	  System.out.println(ageSum);
	  System.out.println((long)(0L + gQData.get(0).age + gQData.get(0).age));
	  System.out.println((int)(1.0 * ageSum/count));
	  System.out.println(now - (int)(1.0 * ageSum/count));

	  int seen = solved + unsolved;
	  sb.append(String.format( "Total: %d\n", unseen+seen));
	  if (unseen > 0) sb.append(String.format( "Unseen: %d (%d%%)\n", unseen, (int)(0.5+100*(unseen/(seen+unseen)))));
	  

	  sb.append(  "Solved: " + solved); 
	  if (seen > 0) sb.append(String.format( " (%d%%)", (int)(0.5+100*solved/seen)));
	  sb.append(  "\nUnsolved: " + unsolved); 
	  if (seen > 0) sb.append(String.format( " (%d%%)", (int)(0.5+100*unsolved/seen)));
	  sb.append(  "\n");
	  if (solved > 0) sb.append(String.format( "Mean solution time: %.1f s\n", 1.0*totalRating/solved));
	  if (unsolved > 0 || unseen > 0 )sb.append(String.format( "Mean difficulty: %.1f s\n",
	     (100.0*(unsolved+unseen)+totalRating)/(seen+unseen)));
	     
	  if (count > 0) sb.append( "Mean solution age: " +
	    QzFSUtils.secondsToHumanTime(now - (int)(1.0 * ageSum/count), true) + "\n");

	  sb.append( "Oldest solution: " + (t > 0 ? QzFSUtils.secondsToHumanTime(now-t, true) : "never"));
	  return sb.toString();
	}

	
	public String summary (int gQCorrect, int promptQord, long gTotalTime, int now, int gSessionStart) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\nYou answered %d question%s correctly of %d",
		gQCorrect, gQCorrect == 1 ? "" : "s", promptQord));
		if (promptQord > 0) sb.append(String.format
				(" (%.1f%%)", 100.0 * gQCorrect/promptQord));
		sb.append(".\n");
		if (gQCorrect > 0) sb.append(String.format
				("You took on average %.1f seconds to answer correctly.\n",
		1.0 * gTotalTime/gQCorrect));
		if (promptQord > 0 && gQCorrect/promptQord > 0.9) sb.append("Congratulations!\n");
		int elapsed = now - gSessionStart;
		sb.append(String.format("Elapsed time: %d:%02d:%02d\n", 
				(int)(elapsed/3600.0), 
				(int)(elapsed/60.0) % 60, 
				elapsed % 60));
		//sb.append("\nCurrent statistics for this question set:\n");
		return sb.toString();
	}
	
	public int addQ(int rating, int age, boolean unseen) {
		QzData d = new QzData();
		d.rating = rating;
		d.age = age;
		d.unseen = unseen;
		gQData.add(d);
		return gQData.size();
	}


	public static void main(String[] args) {
		List<QzData> lis = new ArrayList<>();
		
		boolean unseen = true;
		
		QzFSStats2 qs = new QzFSStats2(lis, QzFSUtils.time());
		for (int i = 0; i < 4; i++) {
			qs.addQ(100, 0, unseen);
			unseen = !unseen;
		}

		String s = qs.doListStats();
		System.out.println(s);
	}
	
}
