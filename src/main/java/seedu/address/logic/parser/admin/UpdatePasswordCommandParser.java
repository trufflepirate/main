package seedu.address.logic.parser.admin;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.admin.AddAdminCommand;
import seedu.address.logic.commands.admin.UpdatePasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;

/**
 * Parses input arguments and creates a new UpdatePasswordCommand object
 */
public class UpdatePasswordCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdatePasswordCommand
     * and returns an UpdatePasswordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdatePasswordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAdminCommand.MESSAGE_USAGE));
        }

        String [] temp = trimmedArgs.split(" ");

        if (temp.length != 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAdminCommand.MESSAGE_USAGE));
        }

        Username username = new Username(temp[0]);
        Password oldPassword = new Password(temp[1]);
        Password newPassword = new Password(temp[2]);
        Password passwordVerify = new Password(temp[3]);

        return new UpdatePasswordCommand(username, oldPassword, newPassword, passwordVerify);
    }
}
