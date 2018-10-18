package seedu.address.model;

import static java.util.Objects.requireNonNull;
import java.util.logging.Logger;
import seedu.address.commons.core.LogsCenter;

/**
 * Returns a message based on the model execution
 */
public class ModelMessageResult {

    private static final Logger logger = LogsCenter.getLogger(ModelMessageResult.class);

    public final String feedbackToUser;
    public final Boolean modelExecutionResult;

    public ModelMessageResult(Boolean modelExecutionResult, String feedbackToUser) {
        this.modelExecutionResult = requireNonNull(modelExecutionResult);
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }

    /**
     * Prints the result
     */
    public void printResult() {
        logger.info((modelExecutionResult ? "Successful" : "Unsuccessful") + " : " + feedbackToUser);

    }

}
