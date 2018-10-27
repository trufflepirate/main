package seedu.address.logic.parser.job;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.job.RequestDeletionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.job.JobName;

/**
 * Parses input arguments and creates a new RequestDeletionCommand object
 */
public class RequestDeletionCommandParser implements Parser<RequestDeletionCommand> {
    /**
     * Parses the give {@code String} of arguments in the context of RequestDeletionJobCommand
     * and returns a RequestDeletionCommand object for execution
     *
     * @throws ParseException if the user input does not conform to the format
     */

    @Override
    public RequestDeletionCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME) || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RequestDeletionCommand.MESSAGE_USAGE));
        }

        JobName jobName = ParserUtil.parseJobName(argumentMultimap.getValue(PREFIX_NAME).get());

        return new RequestDeletionCommand(jobName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


