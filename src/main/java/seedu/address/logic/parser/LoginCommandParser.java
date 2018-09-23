package seedu.address.logic.parser;


import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class LoginCommandParser implements Parser<LoginCommand> {
    public LoginCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoginCommand.MESSAGE_USAGE));
        }

        String [] temp = trimmedArgs.split(" ");
        String username = temp[0];
        String password = temp[1];

        return new LoginCommand(username, password);
    }
}
