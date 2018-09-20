package seedu.address.model.job;

import java.util.Date;


/**
 * Represents a TimeStamp Object for Machines.
 */

public class TimeStamp {

    private final Date createdTimeStamp;

    public TimeStamp() {
        this.createdTimeStamp = new Date(System.currentTimeMillis());
    }

    private long getTime() {
        return createdTimeStamp.getTime();
    }

    private long getDate() {
        return createdTimeStamp.getTime();
    }

    private long getElapsedTime() {
        return System.nanoTime() - createdTimeStamp.getTime();
    }

}
