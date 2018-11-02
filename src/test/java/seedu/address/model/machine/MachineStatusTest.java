package seedu.address.model.machine;

import static junit.framework.TestCase.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.job.Job;
import seedu.address.model.tag.Tag;

public class MachineStatusTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Machine testMachine;
    @Before
    public void setup() {
        testMachine = new Machine( new MachineName("test"), new ArrayList<Job>(), new HashSet<Tag>(), MachineStatus.ENABLED);
    }


    @Test
    public void checkEnabledMachineStatus() {
        assertTrue(testMachine.getStatus() == MachineStatus.ENABLED);
    }
}
