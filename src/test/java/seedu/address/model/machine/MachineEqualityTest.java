package seedu.address.model.machine;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.MachineBuilder;
import seedu.address.testutil.testdata.ValidMachines;



public class MachineEqualityTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Machine testMachine;

    @Before
    public void setup() {
        testMachine = ValidMachines.JJPRINTER;
    }

    @Test
    public void isSameNameMachine() {
        Machine toCheck = new MachineBuilder()
            .withName("JJPrinter")
            .withStatus(MachineStatus.ENABLED)
            .build();
        assertTrue(testMachine.isSameNamedMachine(toCheck));
    }

    @Test
    public void isSameMachineParameters() {
        Machine toCheck = new MachineBuilder()
            .withName("JJPrinter")
            .withStatus(MachineStatus.ENABLED)
            .build();
        assertTrue(testMachine.hasSameMachineParameters(toCheck));
    }

    @Test
    public void isSameMachine() {
        Machine toCheck = new MachineBuilder()
            .withName("JJPrinter")
            .withStatus(MachineStatus.ENABLED)
            .build();

        assertTrue(toCheck.isSameMachine(testMachine));

    }

    @Test
    public void equals() {
        Machine toCheck = new MachineBuilder()
            .withName("JJPrinter")
            .withStatus(MachineStatus.ENABLED)
            .build();


        assertTrue(toCheck.equals(testMachine));
    }

    @Test
    public void notEquals() {
        Machine toCheck = new MachineBuilder()
            .withName("Printer")
            .withStatus(MachineStatus.ENABLED)
            .build();

        assertTrue(!toCheck.equals(testMachine));
    }
}
