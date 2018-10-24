package seedu.address.model.job.exceptions;

/**
 * Signals that the job has not been started yey
 */
public class JobNotStartedException extends Exception{
    public JobNotStartedException() {
        super("This job has not been started yet");
    }
}
