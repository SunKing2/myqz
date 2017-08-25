package myqz;

public class Question {
	protected String question;
	protected String answer;
	protected int rating;
	
	Question(String question, String answer, int rating) {
		this.question = question;
		this.answer = answer;
		this.rating = rating;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}



}
