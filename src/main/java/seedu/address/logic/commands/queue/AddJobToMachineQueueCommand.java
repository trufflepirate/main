package seedu.address.logic.commands.queue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MACHINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelMessageResult;


/**
 * Adds a job to a machine's queue manually
 */
public class AddJobToMachineQueueCommand extends Command {
    public static final String COMMAND_WORD = "addJobToMachine";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a job to the machine's queue"
            + " Parameters: "
            + PREFIX_NAME  + "JOB NAME "
            + PREFIX_MACHINE + "MACHINE NAME "
            + "\nEXAMPLE: \n" + COMMAND_WORD + " "
            + PREFIX_NAME + "iDCP_project "
            + PREFIX_MACHINE + "PRINTER_1 ";
    public static final String MESSAGE_SUCCESS = "New print job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This print job has already been added to machine queue";

    private final String chosenMachine;
    private final String jobToAdd;

    /**
     * Creates an AddJobToMachineQueueCommand with two
     * paramters, {@code machine} and {@code job} where
     * machine is the machine chosen for the chosen job
     * to be added to its queue
     */

    public AddJobToMachineQueueCommand(String chosenMachine, String jobToAdd) {
        requireNonNull(chosenMachine);
        requireNonNull(jobToAdd);
        this.chosenMachine = chosenMachine;
        this.jobToAdd = jobToAdd;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        ModelMessageResult modelMessageResult = model.addJobToMachine(chosenMachine, jobToAdd);
        modelMessageResult.printResult();
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, chosenMachine + " " + jobToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddJobToMachineQueueCommand // instanceof handles nulls
                && jobToAdd.equals(((AddJobToMachineQueueCommand) other).jobToAdd)
                && chosenMachine.equals(((AddJobToMachineQueueCommand) other).chosenMachine));
    }
}
