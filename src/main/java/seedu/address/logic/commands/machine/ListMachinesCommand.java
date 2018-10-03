package seedu.address.logic.commands.machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MACHINES;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListMachinesCommand extends Command {

    public static final String COMMAND_WORD = "listMachines";

    public static final String MESSAGE_SUCCESS = "Listed all Machines";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredMachineList(PREDICATE_SHOW_ALL_MACHINES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
