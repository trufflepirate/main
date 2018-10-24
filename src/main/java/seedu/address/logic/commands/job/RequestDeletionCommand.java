package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.JobName;

/**
 * Request a job to be deleted by admin
 */
public class RequestDeletionCommand extends Command {

    public static final String COMMAND_WORD = "requestDeletion";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Requests a job to be deleted"
            + "\nParameters: "
            + PREFIX_NAME + "JOB NAME"
            + "\nEXAMPLE: " + COMMAND_WORD + " "
            + PREFIX_NAME + "iDCP";

    public static final String MESSAGE_SUCCESS = "Deletion requested: %1$s";
    public static final String MESSAGE_FAILURE = "Deletion NOT requested: %1$s";


    private final JobName jobName;

    public RequestDeletionCommand(JobName jobName) {
        requireNonNull(jobName);
        this.jobName = jobName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.requestDeletion(jobName);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, jobName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RequestDeletionCommand)
                && jobName.equals(((RequestDeletionCommand) other).jobName);
    }
}
