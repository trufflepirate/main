package seedu.address.logic.commands.machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;

/**
 * Removes a machine from MakerManager address book
 */
public class RemoveMachineCommand extends Command {

    public static final String COMMAND_WORD = "removeMachine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a machine from Maker Manager address book"
            + "Parameters : "
            + PREFIX_NAME + "MACHINE NAME "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "JJPrinter ";

    public static final String MESSAGE_REMOVE_MACHINE_SUCCESS = "Machine removed: %1$s";
    public static final String MESSAGE_MACHINE_NOT_FOUND  = "Machine not found";
    private static final String MESSAGE_ACCESS_DENIED =
        "Non admin user is not allowed to remove a machine from maker manager";

    private final MachineName machineName;

    /**
     * @param machineName is the name of the machine to be removed
     */

    public RemoveMachineCommand(MachineName machineName) {
        this.machineName = machineName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isLoggedIn()) {
            throw new CommandException(MESSAGE_ACCESS_DENIED);
        }

        Machine machineToRemove = model.findMachine(machineName);

        if (machineToRemove == null) {
            throw new CommandException(MESSAGE_MACHINE_NOT_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_REMOVE_MACHINE_SUCCESS, machineToRemove));
    }
}
