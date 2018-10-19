package seedu.address.model.job;

/**
 * Indicates different types of job priority
 */
public enum Priority {

    URGENT,
    HIGH,
    NORMAL;

    public static final String MESSAGE_PRIORITY_CONSTRAINTS =
            "Job priority should only be specified as URGENT, HIGH or NORMAL";

    /**
     *checks if the input priority is a acceptable priority for the job
     */
    public static boolean isValidPriority(Priority priority) {
        boolean isValid = true;
        if (priority != URGENT || priority != HIGH || priority != NORMAL) {
            isValid = false;
        }
        return isValid;
    }
}
