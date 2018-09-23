package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.testutil.EventsCollectorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoginCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void execute_login_throwsCommandException() throws Exception {
        thrown.expect(CommandException.class);
    }
}
