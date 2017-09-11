package qz;

public class QzQuestion {
	// use Integer, Long rather than int, long because need to sort them later
	public String question;
	public String answer;
	public Integer rating;
	public boolean unseen = false;
	public Long age;
	public String flags;
	public Integer fileOrder = 0;

	public QzQuestion(String questionDataLine, int fileOrder) {
		String[] fields = questionDataLine.split("\\t");
		this.question = fields[0];
		this.answer = fields[1];
		if (fields[2].charAt(0) == '+') this.unseen = true;
		this.rating = Integer.parseInt(fields[2]);
		this.age = Long.parseLong(fields[3]);
		this.flags = fields[4];
		this.fileOrder = fileOrder;
	}
}
