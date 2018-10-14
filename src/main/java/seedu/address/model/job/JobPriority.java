package seedu.address.model.job;

/**
 * Indicates three different types of job priority
 */
public enum JobPriority {

    HIGH,
    MEDIUM,
    LOW;

    public static final String MESSAGE_JOBPRIORITY_CONSTRAINTS =
            "Job priority should only be specified as HIGH, MEDIUM or LOW";

    public static boolean isValidPriority(JobPriority priority) {
        boolean isValid = true;
        if(priority != HIGH || priority != MEDIUM || priority != LOW) {
            isValid = false;
        }
        return isValid;
    }
}
