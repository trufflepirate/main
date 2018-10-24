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

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-HH-mm-ss");

    public TimeStamp() {
        timeString = timeFormat.format(calendar.getTime());
        timeStringArray = timeString.split("-");
        this.time = convertTimeString(timeStringArray);
    }

    /**
     * gives the information about date and time
     * @return the formatted string showing the time
     */
    public String showTime() {
        return timeStringArray[1] + "/" + timeStringArray[0] + " "
            + timeStringArray[2] + ":" + timeStringArray[3] + ":" + timeStringArray[4];
    }

    public Integer[] getTime() {
        return time;
    }


    /**
     * Compares two timestamps and returns true
     * if {@code timestamp1} is earlier than
     * {@code timestamp2}
     */
    public static boolean compareTime(String time1, String time2) {
        String[] splitTime1 = time1.split(" ");
        String[] splitTime2 = time2.split(" ");
        Integer[] deviation = new Integer[5];


        int month1 = Integer.valueOf(splitTime1[0].split("/")[0]);
        int date1 = Integer.valueOf(splitTime1[0].split("/")[1]);
        int hour1 = Integer.valueOf(splitTime1[1].split(":")[0]);
        int minute1 = Integer.valueOf(splitTime1[1].split(":")[1]);
        int second1 = Integer.valueOf(splitTime1[1].split(":")[2]);

        int month2 = Integer.valueOf(splitTime2[0].split("/")[0]);
        int date2 = Integer.valueOf(splitTime2[0].split("/")[1]);
        int hour2 = Integer.valueOf(splitTime2[1].split(":")[0]);
        int minute2 = Integer.valueOf(splitTime2[1].split(":")[1]);
        int second2 = Integer.valueOf(splitTime2[1].split(":")[2]);

        deviation[0] = month1 - month2;
        deviation[1] = date1 - date2;
        deviation[2] = hour1 - hour2;
        deviation[3] = minute1 - minute2;
        deviation[4] = second1 - second2;

        for (int i = 0; i < 5; i++) {
            if (deviation[i] == 0) {
                continue;
            }else if (deviation[i] < 0) {
                return true;
            }else {
                return false;
            }
        }

        return true;
    }

    /**
     * Converts the split strings of time to the format of Integers for processing
     * @param timeString
     * @return results
     */
    public Integer[] convertTimeString(String[] timeString) {
        Integer[] result = new Integer[timeString.length];

        for (int i = 0; i < timeString.length; i++) {
            result[i] = Integer.parseInt(timeString[i]);
        }

        return result;
    }
}
