package seedu.address.model.machine;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.testutil.testdata.ValidMachines;

public class UniqueMachineListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueMachineList uniqueMachineList = new UniqueMachineList();

    @Test
    public void contains_nullMachine_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueMachineList.contains(null);
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueMachineList.contains(ValidMachines.TYPRINTER));
    }

    @Test
    public void contains_machineInList_returnsTrue() {
        uniqueMachineList.add(ValidMachines.JJPRINTER);
        assertTrue(uniqueMachineList.contains(ValidMachines.JJPRINTER));
    }

}
