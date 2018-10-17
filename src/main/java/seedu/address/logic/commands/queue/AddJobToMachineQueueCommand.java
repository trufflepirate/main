package seedu.address.logic.commands.queue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MACHINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;
import seedu.address.model.machine.Machine;



/**
 * Adds a job to a machine's queue manually
 */
public class AddJobToMachineQueueCommand extends Command {
    public static final String COMMAND_WORD = "addJobToMachine";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a job to the machine's queue"
            + "Paramaters: "
            + PREFIX_NAME  + "JOB NAME "
            + PREFIX_MACHINE + "MACHINE NAME "
            + "EXAMPLE: " + COMMAND_WORD + " "
            + PREFIX_NAME + "iDCP project "
            + PREFIX_MACHINE + "PRINTER 1 ";
    public static final String MESSAGE_SUCCESS = "New print job added: %l$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This print job has already been added to machine queue";

    private final Machine chosenMachine;
    private final Job jobToAdd;

    /**
     * Creates an AddJobToMachineQueueCommand with two
     * paramters, {@code machine} and {@code job} where
     * machine is the machine chosen for the chosen job
     * to be added to its queue
     */

    public AddJobToMachineQueueCommand(Machine chosenMachine, Job jobToAdd) {
        requireNonNull(chosenMachine);
        requireNonNull(jobToAdd);
        this.chosenMachine = chosenMachine;
        this.jobToAdd = jobToAdd;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        return new CommandResult("Hello");
    }
}
