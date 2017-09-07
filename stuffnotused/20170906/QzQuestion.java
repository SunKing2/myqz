package myqz;

public class QzQuestion extends Question {
	
	protected String flags;
	protected int whenAsked;
	protected int readInOrder;

	QzQuestion(String question, String answer, int rating, int age, String flags) {
		super(question, answer, rating);
		this.age = age;
		this.flags = flags;
	}
	
//   nothing useful below this line
	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public int getWhenAsked() {
		return whenAsked;
	}

	public void setWhenAsked(int whenAsked) {
		this.whenAsked = whenAsked;
	}

	public int getReadInOrder() {
		return readInOrder;
	}

	public void setReadInOrder(int readInOrder) {
		this.readInOrder = readInOrder;
	}

	
	// nothing useful from here on, or above here either, 
	// put your code before previous useless setters/getters
}
