package myqz;

public class QzQuestion extends Question {
	
	protected int age;
	protected String flags;

	QzQuestion(String question, String answer, int rating, int age, String flags) {
		super(question, answer, rating);
		this.age = age;
		this.flags = flags;
	}
	
//   nothing useful below this line
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}
    // nothing useful from here on, or above here either, 
	// put your code before previous useless setters/getters
}
