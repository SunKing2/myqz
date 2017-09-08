package myqz2;

import java.util.concurrent.TimeUnit;

public class QzUtils {
	public static String secondsToHuman(long seconds) {
		String sReturn = "";
		long d = TimeUnit.SECONDS.toDays(seconds);
		if (d > 0)
			sReturn = "" + d + " d";
		else {
			long h = TimeUnit.SECONDS.toHours(seconds);
			if (h > 0)
				sReturn = "" + h + " h";
			else {
				long m = TimeUnit.SECONDS.toMinutes(seconds);
				if (m > 0)
					sReturn = "" + m + " m";
				else {
					long s = TimeUnit.SECONDS.toSeconds(seconds);
					sReturn = "" + s + " s";
				}
			}
		}
		return sReturn;
	}
}
