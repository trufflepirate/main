package seedu.address.model.machine;

import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MachineStatusTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Machine testMachine;
    @Before
    public void setup() {
        testMachine = new Machine("test");
    }


    @Test
    public void checkEnabledMachineStatus() {
        assertTrue(testMachine.getStatus() == MachineStatus.ENABLED);
    }
}
