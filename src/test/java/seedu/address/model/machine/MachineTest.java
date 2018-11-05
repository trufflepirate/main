package seedu.address.model.machine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.job.Job;
import seedu.address.model.job.Status;
import seedu.address.model.job.exceptions.DuplicateJobException;
import seedu.address.testutil.testdata.ValidJobs;
import seedu.address.testutil.testdata.ValidMachines;

public class MachineTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void canAddJobToMachine() {
        Machine testMachine = ValidMachines.JJPRINTER;
        testMachine.addJob(ValidJobs.bumberbee);
    }

    @Test
    public void addDuplicateJobToMachine() {
        thrown.expect(DuplicateJobException.class);
        Machine testMachine = ValidMachines.JJPRINTER;
        testMachine.addJob(ValidJobs.IDCP);
        testMachine.addJob(ValidJobs.IDCP);
    }

    @Test
    public void isValidMachineName() {
        assertTrue(Machine.isValidName("JJPrinter2"));
    }

    @Test
    public void isInvalidMachineName() {
        assertTrue(Machine.isValidMachine("JJ JJ JJ "));
    }

    @Test
    public void hasCleanableJobs() {
         Machine testMachine = ValidMachines.JJPRINTER;
         Job toAddJob = ValidJobs.IDCP;
         toAddJob.setStatus(Status.CANCELLED);
         testMachine.addJob(toAddJob);
         assertTrue(testMachine.hasCleanableJobs());
    }

    @Test
    public void canCleanMachine() {
        Machine testMachine = ValidMachines.JJPRINTER;
        Job toAddJob = ValidJobs.IDCP;
        toAddJob.setStatus(Status.CANCELLED);
        testMachine.addJob(toAddJob);
        testMachine.cleanMachine();
        assertTrue(!testMachine.hasCleanableJobs());
    }

    @Test
    public void hasJobsStill() {
        Machine testMachine = ValidMachines.JJPRINTER;
        testMachine.addJob(ValidJobs.IDCP);
        assertTrue(testMachine.hasJobs());
    }

    @Test
    public void canFlushMachine() {
        Machine testMachine = ValidMachines.JJPRINTER;
        testMachine.addJob(ValidJobs.IDCP);
        testMachine.flushMachine();
        assertTrue(!testMachine.hasJobs());
    }

    @Test
    public void getFilteredJobListPredicate() {
        Machine testMachine = ValidMachines.JJPRINTER;
        assertNotNull(testMachine.getFilteredJobListPredicate());
    }

}
