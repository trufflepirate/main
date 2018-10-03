package seedu.address.model.job;

import java.util.Date;

/**
 * Represents a TimeStamp Object for Job.
 * The Timestamp will record the time information while the job started
 */
public class TimeStamp {

    private final Date createdTimeStamp;

    public TimeStamp() {
        this.createdTimeStamp = new Date(System.currentTimeMillis());
    }

    public long getTime() {
        return createdTimeStamp.getTime();
    }

}
