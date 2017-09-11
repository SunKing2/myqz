package qz;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	// a generic method which reads a file as a string
	// it's not really related specifically to this class
	public static String readFileAsString(String fileName) throws IOException {
		String sReturn = "";
		
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			sReturn = new String(encoded, Charset.defaultCharset());
		}
		catch (Exception exc) {
			System.out.println("readFile exc:" + exc);
			throw new IOException("" + exc);
		}
		return sReturn;
	}
}
