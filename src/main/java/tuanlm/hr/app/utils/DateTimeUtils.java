package tuanlm.hr.app.utils;

import java.time.Duration;

/**
 * The Class DateTimeUtils.
 */
public class DateTimeUtils {
	
	/**
	 * Format duration.
	 *
	 * @param duration the duration
	 * @return the string
	 */
	public static String formatDurationToTimeString(Duration duration) {
	    long seconds = duration.getSeconds();
	    long absSeconds = Math.abs(seconds);
	    String positive = String.format(
	        "%02d:%02d:%02d",
	        absSeconds / 3600,
	        (absSeconds % 3600) / 60,
	        absSeconds % 60);
	    return seconds < 0 ? "-" + positive : positive;
	}
}
