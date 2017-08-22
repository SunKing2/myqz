package myqz;

public class MyQz {

	public static void main(String[] args) {
		new MyQz().doIt();

	}
	public void doIt() {
	}

	
	public int score(boolean correct, int originalScore) {
		if (!correct) {
			return 100;
		}
		return (1 + 2 * originalScore/3);
	}
}
