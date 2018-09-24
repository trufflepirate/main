package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;

/**
 * Lets the admin login to MakerManager
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";
    public static final String MESSAGE_SUCCESS = "login success!";
    public static final String MESSAGE_FAILURE = "login failed!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Login used for admin access.\n"
            + "Example: login USERNAME PASSWORD\n";

    private final Username username;
    private final Password password;


    public LoginCommand(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult("username=" + this.username + " pw=" + this.password);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoginCommand // instanceof handles nulls
                && username.equals(((LoginCommand) other).username)
                && password.equals(((LoginCommand) other).password)); // state check
    }
}
