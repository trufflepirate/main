package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;
import seedu.address.ui.testutil.EventsCollectorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    public ExpectedException thrown = ExpectedException.none();

    /*
    @Test
    public void execute_login_throwsCommandException() throws Exception {
        thrown.expect(CommandException.class);
    }
    */

    @Test
    public void equals() {
        Username bob = new Username("bob");
        Username alice = new Username("alice");
        Password alicePw = new Password("123456");
        Password bobPw = new Password("abcdef");

        LoginCommand LoginAliceCommand = new LoginCommand(alice, alicePw);
        LoginCommand LoginBobCommand = new LoginCommand(bob, bobPw);

        // same object -> returns true
        assertTrue(LoginAliceCommand.equals(LoginAliceCommand));

        // same values -> returns true
        LoginCommand LoginAliceCommandCopy = new LoginCommand(alice, alicePw);
        assertTrue(LoginAliceCommand.equals(LoginAliceCommandCopy));

        // different types -> returns false
        assertFalse(LoginAliceCommand.equals(1));

        // null -> returns false
        assertFalse(LoginAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(LoginAliceCommand.equals(LoginBobCommand));
    }

}
