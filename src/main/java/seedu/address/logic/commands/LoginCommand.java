package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lets the admin login to MakerManager
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";
    public static final String MESSAGE_SUCCESS = "login success!";
    public static final String MESSAGE_FAILURE = "login failed!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Login used for admin access.\n"
            + "Example: login USERNAME PASSWORD\n";

    private final String username;
    private final String password;


    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult("username=" + this.username + " pw=" + this.password);
    }
}
