package seedu.address.model.job.exceptions;

/**
 * Signals that the operation will result in duplicate Jobs (Jobs are considered duplicates if they have the same
 * identity).
 */
public class NotValidPriorityException extends RuntimeException {
    public NotValidPriorityException() {
        super("The priority can only be: URGENT, HIGH and NORMAL.");
    }
}

