package seedu.address.logic.commands.admin;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.AdminLoginEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Lets the admin login to MakerManager
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";
    public static final String MESSAGE_SUCCESS = "login success!";
    public static final String MESSAGE_WRONG_DETAILS = "Login failed! Wrong Username/Password.";
    public static final String MESSAGE_ALREADY_LOGGED_IN = "Login failed! "
            + "Please Logout of current account before logging in again.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Login used for admin access.\n"
            + "Example: login USERNAME PASSWORD\n";

    private final Username username;
    private final Password password;
    private final Admin toLogIn;

    public LoginCommand(Username username, Password password) {
        this.username = username;
        this.password = password;
        this.toLogIn = new Admin(this.username, this.password);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        if (model.isLoggedIn()) {
            throw new CommandException(MESSAGE_ALREADY_LOGGED_IN);
        } else if (!BCrypt.checkpw(password.toString(), model.findAdmin(username).getPassword().toString())) {
            throw new CommandException(MESSAGE_WRONG_DETAILS);
        }

        model.setLogin(username);
        model.commitAddressBook(); //TODO: not sure what this does;

        EventsCenter.getInstance().post(new AdminLoginEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoginCommand // instanceof handles nulls
                && username.equals(((LoginCommand) other).username)
                && password.equals(((LoginCommand) other).password)); // state check
    }
}
