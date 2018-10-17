package seedu.address.model;

import static java.util.Objects.requireNonNull;

/**
 * Returns a message based on the model execution
 */
public class ModelMessageResult {

    public final String feedbackToUser;
    public final Boolean modelExecutionResult;
    public ModelMessageResult(Boolean modelExecutionResult, String feedbackToUser) {
        this.modelExecutionResult = requireNonNull(modelExecutionResult);
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }
}
