package myqz;

import myqz.QzFSUtils;

public class QzFSAnswer {
	public String question = "";
	public String answer = "";
	public int oldRating = 0;
	public int oldAge = 0;
	
	public String response = "";
	public int whenAsked = 0;
	public double secondsToAnswer = -1.0;
	public int questionOrder = -1;

	public int newRating = 0;
	public int newAge = 0;
	
	boolean correct = false;
	int timeSincePrevious = 0;
	
	public QzFSAnswer(String answer, int oldRating, int oldAge) {
		this.answer = answer;
		this.oldRating = oldRating;
		this.oldAge = oldAge;
	}
	
	public void respond(String response, int whenAsked, double secondsToAnswer, int questionOrder){
		this.response = response;
		this.whenAsked = whenAsked;
		this.secondsToAnswer = secondsToAnswer;
		this.questionOrder = questionOrder;
		int whenAnswered = (int)(whenAsked + secondsToAnswer);
		correct = response.toUpperCase() == answer.toUpperCase();
		update(calculateNewRating(correct, secondsToAnswer), whenAnswered);
		// TODO could be dangerous on a question without a previous
		timeSincePrevious = whenAnswered - oldAge;
	}
	
	public String getHumanTimeSincePrevious() {
		if (oldRating < 1) {
			return "never";
		}
		return QzFSUtils.iSecondsToHumanTime(timeSincePrevious);
	}
	
	private int calculateNewRating(boolean isCorrect, double secondsToAnswer) {
		if (isCorrect) {
			return (int)((1 + 2.0 * oldRating + secondsToAnswer) / 3.0);
		}
		return 100;
	}

	public void update(int newRating, int newAge) {
		this.newRating = newRating;
		this.newAge = newAge;
	}
}
