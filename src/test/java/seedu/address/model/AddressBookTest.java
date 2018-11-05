package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.testdata.TypicalPersons.ALICE;
import static seedu.address.testutil.testdata.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.JobMachineTuple;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.AdminSession;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobName;
import seedu.address.model.job.Status;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.builders.PersonBuilder;
import seedu.address.testutil.testdata.ValidJobs;
import seedu.address.testutil.testdata.ValidMachines;

public class AddressBookTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        addressBook.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice =
            new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        thrown.expect(DuplicatePersonException.class);
        addressBook.resetData(newData);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        addressBook.hasPerson(null);
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice =
            new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getPersonList().remove(0);
    }

    @Test
    public void canRemoveMachine() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        addressBook.removeMachine(ValidMachines.JJPRINTER);
        assertNull(addressBook.findMachine(new MachineName("JJPrinter")));
    }

    @Test
    public void canFlushMachine() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        addressBook.flushMachine(ValidMachines.JJPRINTER);
        Machine flushedMachine = addressBook.findMachine(new MachineName("JJPrinter"));
        assertTrue(!flushedMachine.hasJobs());
    }

    @Test
    public void canCleanMachine() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        Job toAddJob = ValidJobs.IDCP;
        toAddJob.setStatus(Status.CANCELLED);
        addressBook.addJobToMachineList(toAddJob);
        addressBook.cleanMachine(ValidMachines.JJPRINTER);
        Machine cleanedMachine = addressBook.findMachine(new MachineName("JJPrinter"));
        assertTrue(!cleanedMachine.hasCleanableJobs());
    }

    @Test
    public void canAddJobToMachineList() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        Job toAddJob = ValidJobs.IDCP;
        addressBook.addJobToMachineList(toAddJob);
        Machine foundJob = addressBook.findMachine(new MachineName("JJPrinter"));
        assertTrue(foundJob.hasJob(toAddJob));
    }

    @Test
    public void canStartJobInMachineList() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        Job toAddJob = ValidJobs.IDCP;
        addressBook.addJobToMachineList(toAddJob);
        addressBook.startJob(new JobName(toAddJob.getJobName().fullName));
        JobMachineTuple foundJobMachineTuple = addressBook.findJob(new JobName(toAddJob.getJobName().fullName));
        Job foundJob = foundJobMachineTuple.job;
        assertTrue(foundJob.getStatus().equals(Status.ONGOING));
    }

    @Test
    public void canCancelJobInMachineList() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        Job toAddJob = ValidJobs.IDCP;
        addressBook.addJobToMachineList(toAddJob);
        addressBook.startJob(new JobName(toAddJob.getJobName().fullName));
        JobMachineTuple foundJobMachineTuple = addressBook.findJob(new JobName(toAddJob.getJobName().fullName));
        Job foundJob = foundJobMachineTuple.job;
        addressBook.cancelJob(new JobName(toAddJob.getJobName().fullName));
        assertTrue(foundJob.getStatus().equals(Status.CANCELLED));
    }

    @Test
    public void canRestartJobInMachineList() {
        addressBook.addMachine(ValidMachines.JJPRINTER);
        Job toAddJob = ValidJobs.IDCP;
        addressBook.addJobToMachineList(toAddJob);
        addressBook.startJob(new JobName(toAddJob.getJobName().fullName));
        JobMachineTuple foundJobMachineTuple = addressBook.findJob(new JobName(toAddJob.getJobName().fullName));
        Job foundJob = foundJobMachineTuple.job;
        addressBook.restartJob(new JobName(toAddJob.getJobName().fullName));
        assertTrue(foundJob.getStatus().equals(Status.ONGOING));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Admin> admins = FXCollections.observableArrayList();
        private final ObservableList<Machine> machines = FXCollections.observableArrayList();
        private final AdminSession adminSession = new AdminSession();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Admin> getAdminList() {
            return admins;
        }

        //TODO havent integrated this code properly yet
        @Override
        public ObservableList<Machine> getMachineList() {
            return machines;
        }

        @Override
        public AdminSession getAdminSession() {
            return adminSession;
        }

        @Override
        public int getTotalNumberOfStoredJobs() {
            return getMachineList().stream().mapToInt(machine -> machine.getJobs().size()).sum();
        }

    }

}
