package myqz;

import java.util.Arrays;

public class QzFSUtils {

	public static String secondsToHumanTime(int seconds, boolean removeDecimal) {
		//return secondsToHumanTime(seconds).replace(".0", "");
		return iSecondsToHumanTime(seconds);
	}
	
	public static String secondsToHumanTime(double seconds) {
		double interval = seconds;
		if (interval < 120) { return "" + interval + " s"; }
		interval = interval/60;
		if (interval < 60) { return "" + interval + " m"; }
		interval = interval/60;
		if (interval < 24) { return "" + interval + " h"; }
		interval = interval/24;
		return "" + interval + " d";
	}
	
	public static String iSecondsToHumanTime(int seconds) {
		int interval = seconds;
		if (interval < 120) { return "" + interval + " s"; }
		interval = interval/60;
		if (interval < 60) { return "" + interval + " m"; }
		interval = interval/60;
		if (interval < 24) { return "" + interval + " h"; }
		interval = interval/24;
		return "" + interval + " d";
	}
	
	public static String alphagram(String word) {
		char[] cs = word.toCharArray();
		Arrays.sort(cs);;
		return new String(cs);
	}
	public static String firstWord(String words) {
		return words.split(" ")[0];
	}
	public static String firstWordAlphagram(String words) {
		return alphagram(firstWord(words));
	}
	public static String durationToString(int seconds) {
		return String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));		
	}
	public static int time() {
		return (int)System.currentTimeMillis() / 1000;
	}

}
