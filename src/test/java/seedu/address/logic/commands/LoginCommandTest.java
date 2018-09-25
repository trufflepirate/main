package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;
import seedu.address.ui.testutil.EventsCollectorRule;


public class LoginCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

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

        LoginCommand loginAliceCommand = new LoginCommand(alice, alicePw);
        LoginCommand loginBobCommand = new LoginCommand(bob, bobPw);

        // same object -> returns true
        assertTrue(loginAliceCommand.equals(loginAliceCommand));

        // same values -> returns true
        LoginCommand loginAliceCommandCopy = new LoginCommand(alice, alicePw);
        assertTrue(loginAliceCommand.equals(loginAliceCommandCopy));

        // different types -> returns false
        assertFalse(loginAliceCommand.equals(1));

        // null -> returns false
        assertFalse(loginAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(loginAliceCommand.equals(loginBobCommand));
    }

}
