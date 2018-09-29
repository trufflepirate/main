package seedu.address.logic.parser.admin;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.admin.RemoveAdminCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.admin.Username;

/**
 * Parses input arguments and creates a new RemoveAdminCommand object
 */
public class RemoveAdminCommandParser implements Parser<RemoveAdminCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveAdminCommand
     * and returns an RemoveAdminCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveAdminCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveAdminCommand.MESSAGE_USAGE));
        }

        String [] temp = trimmedArgs.split(" ");

        if (temp.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveAdminCommand.MESSAGE_USAGE));
        }

        Username username = new Username(temp[0]);

        return new RemoveAdminCommand(username);
    }

}
