package seedu.address.model.job;

import java.util.Calendar;
import java.util.Date;

/**
 * Timestamp for modelling time
 */
public class TimeStamp {
    private Calendar calendar;

    public TimeStamp() {
        this.calendar = Calendar.getInstance();
    }

    public TimeStamp(long millis) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(millis);
    }

    /**
     * Return the calendar
     *
     * @return
     */
    public Calendar getCalendar() {
        return calendar;
    }

    public Date getDate() {
        return this.getCalendar().getTime();
    }

    public static boolean compareTime(TimeStamp time1, TimeStamp time2) {
        return time1.getDate().getTime() <= time2.getDate().getTime();
    }

    public static long timeDifference(TimeStamp time1, TimeStamp time2) {
        return time2.getDate().getTime() - time1.getDate().getTime();
    }

    /**
     * Shows the time
     *
     * @return
     */
    public String showTime() {
        return this.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + this.getCalendar().get(Calendar.MONTH) + " " + this
            .getCalendar().get(Calendar.HOUR) + ":" + this.getCalendar().get(Calendar.MINUTE) + ":" + this.getCalendar()
            .get(Calendar.SECOND);
    }

}
