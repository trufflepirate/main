package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.job.Job;
import seedu.address.model.job.Priority;
import seedu.address.model.job.Status;
import seedu.address.model.machine.MachineStatus;

/**
 * A utility class containing a list of {@code Job} objects to be used in tests.
 */
public class ValidJobs {

    private static final Job IDCP = new JobBuilder()
            .withName("IDCP")
            .withMachine(new MachineBuilder()
                    .withMachineName("JJPrinter")
                    .withMachineStatus(MachineStatus.ENABLED).build())
            .withJobNote("This is jj jobnote")
            .withDuration(2)
            .withPriority(Priority.HIGH)
            .withOwner(new PersonBuilder()
                    .withName("Jun jie")
                    .build())
            .withStatus(Status.QUEUED)
            .build();

    private static final Job newProject = new JobBuilder()
            .withName("newProject")
            .withMachine(new MachineBuilder()
                    .withMachineName("TyPrinter")
                    .withMachineStatus(MachineStatus.ENABLED).build())
            .withJobNote("This is newproject jobnote")
            .withDuration(2)
            .withPriority(Priority.HIGH)
            .withOwner(new PersonBuilder()
                    .withName("TianYuan")
                    .build())
            .withStatus(Status.FINISHED)
            .build();

    private static final Job bumberbee = new JobBuilder()
            .withName("bumblebee")
            .withMachine(new MachineBuilder()
                    .withMachineName("BumberbeePrinter")
                    .withMachineStatus(MachineStatus.ENABLED).build())
            .withJobNote("This is bumberbee jobnote")
            .withDuration(1)
            .withPriority(Priority.NORMAL)
            .withOwner(new PersonBuilder()
                    .withName("Bumble bee")
                    .build())
            .withStatus(Status.CANCELLED)
            .build();



    public static AddressBook getValidMakerManagerJobsData() {
        AddressBook makerManagerData = new AddressBook();
        for (Job job : getValidJobs()) {
            makerManagerData.addJob(job);
        }
        return makerManagerData;
    }

    public static List<Job> getValidJobs() {
        return new ArrayList<>(Arrays.asList(IDCP, newProject, bumberbee));
    }


}
