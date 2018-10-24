package seedu.address.model.job;


/**
 * Represents a TimeStamp Object for Job.
 * The Timestamp will record the time information while the job started
 */
public class TimeStamp {

    private final Long createdTimeStamp;

    public TimeStamp() {
        this.createdTimeStamp = System.currentTimeMillis();
    }

    public long getTime() {getTime();
        return createdTimeStamp;
    }

    /**
     * Compares two timestamps and returns true
     * if {@code timestamp1} is earlier than
     * {@code timestamp2}
     */
    public static boolean compareTimeStamp(TimeStamp timeStamp1, TimeStamp timeStamp2) {
        return timeStamp1.getTime() < timeStamp2.getTime();
    }

}
