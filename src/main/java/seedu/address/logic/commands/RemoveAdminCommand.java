package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.Username;

/**
 * Lets one admin remove another admin
 */
public class RemoveAdminCommand extends Command {
    public static final String COMMAND_WORD = "removeAdmin";
    public static final String MESSAGE_NO_ACCESS = "You must be logged in to remove another admin!";
    public static final String MESSAGE_SUCCESS = "Admin removed successfully!";
    public static final String MESSAGE_NO_SUCH_ADMIN = "No such admins exist. Have you typed the username correctly?";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "Used to remove another admin.\n"
            + "Example: removeAdmin USERNAME\n";

    private final Username username;

    public RemoveAdminCommand(Username username) {
        this.username = username;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        if (!model.isLoggedIn()) {
            throw new CommandException(MESSAGE_NO_ACCESS);
        }

        Admin toRemove = model.findAdmin(username);
        if (toRemove == null) {
            throw new CommandException(MESSAGE_NO_SUCH_ADMIN);
        }

        model.removeAdmin(toRemove);
        model.commitAddressBook();  //TODO: not sure what this does;

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands.RemoveAdminCommand // instanceof handles nulls
                && username.equals(((seedu.address.logic.commands.RemoveAdminCommand) other).username)); // state check
    }

}
