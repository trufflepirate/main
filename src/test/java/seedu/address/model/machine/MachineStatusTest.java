package seedu.address.model.machine;

import static junit.framework.TestCase.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.machine.exceptions.InvalidMachineStatusException;

public class MachineStatusTest {
    private Machine testMachine;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup(){
        testMachine = new Machine("test");
    }
    @Test
    public void checkEnabledMachineStatus() {
        assertTrue(testMachine.getStatus() == MachineStatus.ENABLED);
    }

    @Test
    public void setInvalidMachineStatus() {
        thrown.expect(InvalidMachineStatusException.class);
        testMachine.setMachineStatus(MachineStatus.INVALID);
    }
}
