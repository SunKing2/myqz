package myqz;
import java.util.*;

public class OOQz {
	
	private String dBMSuffix		= ".dir";	
	private String environment		= "unix";
	private String notes			= "";	
	private String qCorrect			= "0";
	private String qCount			= "3";
	private String qCount2			= "0";
	private String sessionStart		= "1503420794";	
	private String totalRating		= "0";	
	private String totalTime		= "0";

	private List<String> errors	= new ArrayList<String>();
	private List<String> FileNumDirty	= new ArrayList<String>(Arrays.asList("0"));
	private List<String> FileNumFileName	= new ArrayList<String>(Arrays.asList("mystuff.qz"));
	private List<String> FileNumQuestionCount	= new ArrayList<String>(Arrays.asList("3"));
	private List<String> QByAge	= new ArrayList<String>();
	private List<String> QByRating	= new ArrayList<String>();
	private List<String> QData	= new ArrayList<String>(Arrays.asList("AQT", "QAT", "100", "0", "CO", "", "IQS", "QIS", "100", "0", "CO", "","AEU", "EAU", "100", "0", "CO", ""));
	private List<String> RatingTree	= new ArrayList<String>();
	private List<String> WhenAsked	= new ArrayList<String>();
	
	private List<Question> questions = new ArrayList<Question>();
	{
		questions.add(new Question("AQT", "QAT", 100));
		questions.add(new Question("IQS", "QIS", 100));
		questions.add(new Question("AEU", "EAU", 100));
	}

	public void initialize() {
		
	}
	public void mungeData() {
		qCount2 = "4";
		totalRating = "300";
		this.QByAge.add("0");
		this.QByAge.add("1");
		this.QByAge.add("2");
		
		// QByRating gets 102 elements, all blank except element 100
		for(int i = 0; i < 102; i++) {
			this.QByRating.add("");
		}
		QByRating.set(100, new String("0 1 2 "));
		
		this.RatingTree.add("100");
		this.RatingTree.add("200");
		this.RatingTree.add("100");
		
		this.WhenAsked.add("-15");
		this.WhenAsked.add("-15");
		this.WhenAsked.add("-15");
	}
	
	public void askQ(int question) {
		this.WhenAsked.set(question, "" + (question + 1));
	}
	
	public void GotIt(int question) {
		this.qCorrect = "" + (question + 1);
		
		if (question == 0) {
			this.totalRating = "269";
			this.totalTime = "7.55555555555556";
		}
		if (question == 1) {
			this.totalRating = "237";
			this.totalTime = "12.1111111111111";
		}
		if (question == 2) {
			this.totalRating = "205";
			this.totalTime = "15.6666666666667";
		}

		
		this.FileNumDirty.set(0, "1");
		
		// appears that the current question is put at end of list
		String sTmp = this.QByAge.get(0);
		this.QByAge.remove(0);
		this.QByAge.add(sTmp);
		
		// question 0
		// 69 contains "0"
		// 100 "1 2 " (sic)
		// question 1
		// 69 contains "0", 68 contains "1"
		// 100 "2 " (sic)
		// question 2
		// 69 contains "0", 68 contains "1 2 " (sic)
		// 100 ""
		if (question == 0) {
			this.QByRating.set(69, "0");
		}
		if (question == 1) {
			this.QByRating.set(68, "1");
		}
		if (question == 2) {
			this.QByRating.set(68, "1 2 ");
		}
		this.QByRating.set(69 - question, "" + question);
		String sOriginal = "0 1 2 ";
		int iNewIndex = 2 + question * 2;
		String sNewString = sOriginal.substring(iNewIndex);
		//example for question 0:
		//this.QByRating.set(100, "1 2 ");
		this.QByRating.set(100, sNewString);
		
		if(question == 0) {
			// qdata element 2=69, 3=timestamp
			this.QData.set(2, "69");
			this.QData.set(3, "1503427718");
		}
		if(question == 1) {
			this.QData.set(8, "68");
			this.QData.set(9, "1503430295");
		}
		if(question == 2) {
			this.QData.set(14, "68");
			this.QData.set(15, "1503431952");
		}
		
		// ratingtree
		this.RatingTree.set(0, "69");
		
		if(question == 0) {
			this.RatingTree.set(1, "169");
		}
		if(question == 1) {
			this.RatingTree.set(1, "137");
		}
		if(question == 2) {
			this.RatingTree.set(2, "68");
		}
		
		markQuestionPassed(question);
	}
	
	public void markQuestionPassed(int questionNumber) {
		Question q = this.getQuestions().get(questionNumber);
		int oldRating = q.getRating();
		int newRating = 1 + (2 * oldRating / 3);
		q.setRating(newRating);
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public String getdBMSuffix() {
		return dBMSuffix;
	}


	public void setdBMSuffix(String dBMSuffix) {
		this.dBMSuffix = dBMSuffix;
	}


	public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getqCorrect() {
		return qCorrect;
	}


	public void setqCorrect(String qCorrect) {
		this.qCorrect = qCorrect;
	}


	public String getqCount() {
		return qCount;
	}


	public void setqCount(String qCount) {
		this.qCount = qCount;
	}


	public String getqCount2() {
		return qCount2;
	}


	public void setqCount2(String qCount2) {
		this.qCount2 = qCount2;
	}


	public String getSessionStart() {
		return sessionStart;
	}


	public void setSessionStart(String sessionStart) {
		this.sessionStart = sessionStart;
	}


	public String getTotalRating() {
		return totalRating;
	}


	public void setTotalRating(String totalRating) {
		this.totalRating = totalRating;
	}


	public String getTotalTime() {
		return totalTime;
	}


	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}


	public List<String> getFileNumDirty() {
		return FileNumDirty;
	}


	public List<String> getFileNumFileName() {
		return FileNumFileName;
	}


	public List<String> getFileNumQuestionCount() {
		return FileNumQuestionCount;
	}


	public List<String> getQByAge() {
		return QByAge;
	}


	public List<String> getQByRating() {
		return QByRating;
	}


	public List<String> getQData() {
		return QData;
	}


	public List<String> getRatingTree() {
		return RatingTree;
	}


	public List<String> getWhenAsked() {
		return WhenAsked;
	}

	public List<Question> getQuestions() {
		return questions;
	}
}
