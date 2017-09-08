package myqz2;

public class QzQuestion {
	// use Integer, Long rather than int, long because need to sort them later
	public String question;
	public String answer;
	public String srating;
	public String sage;
	public Integer rating;
	public boolean unseen = false;
	public Long age;

	public QzQuestion(String questionDataLine) {
		String[] fields = questionDataLine.split("\\t");
		this.question = fields[0];
		this.answer = fields[1];
		this.srating = fields[2];
		if (fields[2].charAt(0) == '+') this.unseen = true;
		this.rating = Integer.parseInt(fields[2]);
		this.sage = fields[3];
		this.age = Long.parseLong(fields[3]);
	}
}
