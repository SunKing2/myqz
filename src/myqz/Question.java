package myqz;

public class Question {
	protected String question;
	protected String answer;
	protected int rating;
	protected int age = 0;
	
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
