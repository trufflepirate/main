package seedu.address.model.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents a TimeStamp Object for Job.
 * The Timestamp will record the time information while the job started
 */
public class TimeStamp {

    private static Integer[] time;
    private static String timeString;
    private static String[] timeStringArray;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-HH-mm-ss");

    public TimeStamp() {
        timeString = timeFormat.format(calendar.getTime());
        timeStringArray = timeString.split("-");
        this.time = convertTimeString(timeStringArray);
    }

    public String getTime() {
        return timeStringArray[1] + "/" + timeStringArray[0] + " " +
            timeStringArray[2] + ":" + timeStringArray[3] + ":" + timeStringArray[4];
    }


    /**
     * Compares two timestamps and returns true
     * if {@code timestamp1} is earlier than
     * {@code timestamp2}
     */
    public static boolean compareTimeStamp(TimeStamp timeStamp1, TimeStamp timeStamp2) {

        for(int i = 0; i < time.length; i ++) {
            if(timeStamp1.time[i] == timeStamp2.time[i]) continue;
            else if(timeStamp1.time[i] > timeStamp2.time[i]) return false;
            else return true;
        }
        return true;
    }

    public Integer[] convertTimeString(String[] timeString) {
        Integer[] result = new Integer[timeString.length];

        for(int i = 0; i < timeString.length; i ++) {
            result[i] = Integer.parseInt(timeString[i]);
        }

        return result;
    }
}
