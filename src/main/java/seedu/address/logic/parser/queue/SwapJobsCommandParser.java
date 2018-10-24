package seedu.address.logic.parser.queue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.queue.SwapJobsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SwapJobCommand object
 */
public class SwapJobsCommandParser implements Parser<SwapJobsCommand> {


    /**
     * Parses the given {@code userInput} of arguments in the context
     * of SwapJobsCommandParser and returns a SwapJobCommand object
     * @throws ParseException if the user input does not conform to the expected
     * format
     */
    public SwapJobsCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_NAME);

        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_NAME)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SwapJobsCommand.MESSAGE_USAGE));
        }

        Optional<String> jobName1 = argMultiMap.getValue(PREFIX_NAME);
        Optional<String> jobName2 = argMultiMap.getValue(PREFIX_NAME);

        if (!jobName1.isPresent() || jobName2.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SwapJobsCommand.MESSAGE_USAGE));
        }

        return new SwapJobsCommand(jobName1.get(), jobName2.get());
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
