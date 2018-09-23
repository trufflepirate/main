package seedu.address.logic.parser;


import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.admin.PasswordHash;
import seedu.address.model.admin.Username;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class LoginCommandParser implements Parser<LoginCommand> {
    public LoginCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoginCommand.MESSAGE_USAGE));
        }

        String [] temp = trimmedArgs.split(" ");
        Username username = new Username(temp[0]);
        PasswordHash password = new PasswordHash(temp[1]);

        return new LoginCommand(username, password);
    }
}
