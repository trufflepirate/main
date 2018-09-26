package seedu.address.logic.commands.machine;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.machine.Machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a machine to MakerManager address book
 */
public class AddMachineCommand extends Command {

    public static final String COMMAND_WORD = "add_machine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a machine to MakerManager address book. "
            + "Parameters: "
            + PREFIX_NAME + "MACHINE NAME "
            + PREFIX_MACHINE_STATUS + "MACHINE STATUS "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "3D_PRINTER_402 "
            + PREFIX_MACHINE_STATUS +"ENABLED";


    public static final String MESSAGE_SUCCESS = "New machine added: %l$s";
    public static final String MESSAGE_DUPLICATE_MACHINE = " This machine already exists in MakerManager address book";

    private final Machine machineToAdd;

    public AddMachineCommand(Machine machine) {
        requireNonNull(machine);
        this.machineToAdd = machine;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS,machineToAdd));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddMachineCommand) // instanceof handles nulls
            && machineToAdd.equals(((AddMachineCommand) other).machineToAdd);
    }
}
