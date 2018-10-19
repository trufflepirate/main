package seedu.address.logic.parser.queue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MACHINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.queue.AddJobToMachineQueueCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddJobToMachineQueueCommand
 * object
 */

public class AddJobToMachineQueueParser implements Parser<AddJobToMachineQueueCommand> {

    /**
     * Parses the given {@code userInput} of arguments in the context
     * of AddJobToMachineQueueParser and returns an AddJobToMachineQueueParser
     * @throws ParseException if the user input does not conform to the expected format
     * */
    public AddJobToMachineQueueCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_MACHINE);

        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_MACHINE)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddJobToMachineQueueCommand.MESSAGE_USAGE));
        }

        Optional<String> machineName = argMultiMap.getValue(PREFIX_MACHINE);
        Optional<String> jobName = argMultiMap.getValue(PREFIX_NAME);

        if (!machineName.isPresent() || !jobName.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddJobToMachineQueueCommand.MESSAGE_USAGE));
        }
        return new AddJobToMachineQueueCommand(machineName.get(), jobName.get());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
