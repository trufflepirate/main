package seedu.address.model.job;

/**
 * Indicates different statuses of Jobs
 */
public enum Status {
    CANCELLED,
    FINISHED,
    ONGOING,
    QUEUED;

    public static final String MESSAGE_STATUS_CONSTRAINTS =
            "Job status should only be specified as CANCELLED, FINISHED, ONGOING or QUEUED";

    /**
     *checks if the input priority is a acceptable status for the job
     */
    public static boolean isValidStatus(Status status) {
        boolean isValid = true;
        if (status != CANCELLED || status != FINISHED || status != ONGOING || status != QUEUED) {
            isValid = false;
        }
        return isValid;
    }
}
