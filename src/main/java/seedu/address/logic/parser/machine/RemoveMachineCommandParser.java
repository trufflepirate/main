package seedu.address.logic.parser.machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.machine.RemoveMachineCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.machine.MachineName;

/**
 * Parses input arguments and creates a new removeMachineCommand object
 */
public class RemoveMachineCommandParser implements Parser<RemoveMachineCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the removeMachineCommand
     * and returns a removeMachineCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public RemoveMachineCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);
        MachineName machineName;
        try {
            machineName = ParserUtil.parseMachineName(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMachineCommand.MESSAGE_USAGE, pe));
        }
        return new RemoveMachineCommand(machineName);
    }
}
