package seedu.address.logic.commands.admin;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;

/**
 * Command used to update Password of currently logged in Admin
 */
public class UpdatePasswordCommand extends Command {
    public static final String COMMAND_WORD = "updatePassword";
    public static final String MESSAGE_NO_ACCESS = "You must be logged in to change your password!";
    public static final String MESSAGE_SUCCESS = "Your password has been changed successfully!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " is used to change password of logged in admin.\n"
            + "Example: udpatePassword USERNAME OLD_PW NEW_PW NEW_PW_VERIFY\n";
    public static final String MESSAGE_ONLY_CHANGE_YOUR_OWN_PW = "You can only change your own password.";
    public static final String MESSAGE_PASSWORDS_DONT_MATCH = "The two password fields don't match! Please try again.";

    private final Username username;
    private final Password oldPassword;
    private final Password newPassword;
    private final Password passwordVerify;
    private final Admin toUpdate;
    private final Admin updatedAdmin;

    public UpdatePasswordCommand(Username username, Password oldPassword,
                                 Password newPassword, Password passwordVerify) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.passwordVerify = passwordVerify;
        this.toUpdate = new Admin(username, oldPassword);
        this.updatedAdmin = new Admin(username, newPassword);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        if (!model.isLoggedIn()) {
            throw new CommandException(MESSAGE_NO_ACCESS);
        }

        if (!newPassword.equals(passwordVerify)) {
            throw new CommandException(MESSAGE_PASSWORDS_DONT_MATCH);
        }

        if (!username.equals(model.currentlyLoggedIn())) {
            throw new CommandException(MESSAGE_ONLY_CHANGE_YOUR_OWN_PW);
        }

        model.updateAdmin(toUpdate, updatedAdmin);
        model.commitAddressBook();  //TODO: not sure what this does;

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdatePasswordCommand // instanceof handles nulls
                && username.equals(((UpdatePasswordCommand) other).username)
                && newPassword.equals(((UpdatePasswordCommand) other).newPassword)
                && oldPassword.equals(((UpdatePasswordCommand) other).oldPassword)
                && passwordVerify.equals(((UpdatePasswordCommand) other).passwordVerify)); // state check
    }

}
