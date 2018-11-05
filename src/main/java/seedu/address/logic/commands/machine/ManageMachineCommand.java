package seedu.address.logic.commands.machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
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
public class ManageMachineCommand extends Command {

    public static final String COMMAND_WORD = "manageMachine";
    public static final String OPTION_REMOVE = "remove";
    public static final String OPTION_FLUSH = "flush";
    public static final String OPTION_CLEAN = "clean";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": flush/clean/removes a machine from Maker Manager address book"
            + "\nExample: " + COMMAND_WORD + " "
            + "JJPrinter flush";

    public static final String MESSAGE_REMOVE_MACHINE_SUCCESS = "Machine removed: %1$s";
    public static final String MESSAGE_FLUSH_MACHINE_SUCCESS = "Machine has been flushed";
    public static final String MESSAGE_CLEAN_MACHINE_SUCCESS = "Machine has been cleaned";
    public static final String MESSAGE_MACHINE_NOT_FOUND  = "Machine not found";
    private static final String MESSAGE_ACCESS_DENIED =
        "Non admin user is not allowed to manage a machine from maker manager";

    private MachineName machineName;
    private String option;

    /**
     * @param machineName is the name of the machine to be removed
     */

    public ManageMachineCommand(MachineName machineName, String option) {
        requireAllNonNull(machineName, option);
        this.machineName = machineName;
        this.option = option;
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
