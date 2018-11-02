package seedu.address.testutil.builders;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.job.Job;
import seedu.address.model.job.JobName;
import seedu.address.model.job.JobNote;
import seedu.address.model.job.Priority;
import seedu.address.model.job.Status;
import seedu.address.model.job.TimeStamp;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Job objects.
 */

public class JobBuilder {

    public static final String DEFAULT_JOBNAME = "MakerManagerJob";
    public static final Priority DEFAULT_PRIORITY = Priority.URGENT;
    public static final Status DEFAULT_STATUS = Status.ONGOING;
    private static final String DEFAULT_JOBNOTE = "This is makerManager default job";
    private static final long DEFAULT_DURAATION = 3;


    //Identity field
    private JobName name;
    private MachineName machine;
    private TimeStamp startTime;
    private Person owner;
    private final String addedTime;

    //Data field
    private final Set<Tag> tags;
    private JobNote jobNote;
    private Priority priority;
    private Status status;
    private long duration;


    public JobBuilder() {
        name = new JobName(DEFAULT_JOBNAME);
        machine = new MachineBuilder().build().getName();
        owner = new PersonBuilder().build();
        priority = DEFAULT_PRIORITY;
        status = DEFAULT_STATUS;
        jobNote = new JobNote(DEFAULT_JOBNOTE);
        duration = DEFAULT_DURAATION;
        tags = new HashSet<>();

        startTime = new TimeStamp();
        addedTime = startTime.showTime();
    }

    /**
     *
     */
    public JobBuilder withName(String name) {
        this.name = new JobName(name);
        return this;
    }

    /**
     *
     */
    public JobBuilder withMachine(MachineName machine) {
        this.machine = machine;
        return this;
    }

    /**
     *
     */
    public JobBuilder withOwner(Person owner) {
        this.owner = owner;
        return this;
    }

    /**
     *
     */
    public JobBuilder withJobNote(String jobNote) {
        this.jobNote = new JobNote(jobNote);
        return this;
    }

    /**
     *
     */
    public JobBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     *
     */
    public JobBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    /**
     *
     */
    public JobBuilder withDuration(long duration) {
        this.duration = duration;
        return this;
    }

    /**
     *
     */
    public JobBuilder addTags(Tag tag) {
        tags.add(tag);
        return this;

    }

    public Job build() {
        return new Job(name, machine, owner, priority, duration, jobNote, tags);
    }
}
