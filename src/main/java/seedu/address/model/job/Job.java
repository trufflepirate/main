package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.machine.Machine;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Printing job in MakerManager.
 * Morphed from the Person class in original Addressbook
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Job names should only contain alphanumeric characters and spaces, "
                    + "and it should not be blank";

    public static final String MESSAGE_NOTE_CONSTRAINTS =
            "Job notes should only contain alphanumeric characters and spaces, "
                    + "and it should not be blank";

    //Identity field
    private JobName name;
    private Machine machine;
    private TimeStamp startTime;
    private Person owner;

    //Data field
    private final Set<Tag> tags = new HashSet<>();
    private JobNote jobNote;
    private Priority priority;
    private Status status;
    private float duration;

    /**
     * Every field must be present and not null.
     * TODO: Need to validate all these somewhere
     */
    public Job(Name name, Machine machine, Person owner, Priority priority, float duration, JobNote jobNote, Set<Tag> tags) {
        requireAllNonNull(name, machine, owner, tags);
        this.name = (JobName) name;
        this.machine = machine;
        this.owner = owner;
        this.tags.addAll(tags);
        this.priority = priority;
        this.status = Status.QUEUED;
        this.jobNote = jobNote;
        this.duration = duration;

        startTime = new TimeStamp();
    }

    public JobNote getJobNote() {
        return this.jobNote;
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * Used to start a job
     */
    public void startJob() {
        this.status = Status.ONGOING;
        this.startTime = new TimeStamp();
    }

    /**
     * Used in case of failed prints
     */
    public void restartJob() {
        this.startJob();
    }

    public void cancelJob() {
        this.status = Status.CANCELLED;
    }

    public void finishJob() {
        this.status = Status.FINISHED;
    }

    public void setJobNote(String jobNote) {
        this.jobNote.changeNote(jobNote);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JobName getJobName() {
        return name;
    }

    public Machine getMachine() {
        return machine;
    }

    public TimeStamp getStartTime() {
        return startTime;
    }

    public Person getOwner() {
        return owner;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void addNote(String addition) {
        this.jobNote.addNote(addition);
    }


    public void setName(String newName) {
        name = new JobName(newName);
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
    }

    public void setOwner(Person newOwner) {
        owner = newOwner;
    }

    /**
     * Returns true if both jobs of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two jobs.
     * //TODO: Modify to match new class
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getJobName().equals(getJobName())
                && (otherJob.getMachine().equals(getMachine())
                || otherJob.getStartTime().equals(getStartTime())
                || otherJob.getOwner().equals(getOwner()));
    }

    /**
     * Returns true if both jobs have the same identity and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return otherJob.getJobName().equals(getJobName())
                && otherJob.getMachine().equals(getMachine())
                && otherJob.getOwner().equals(getOwner())
                && otherJob.getStartTime().equals(getStartTime())
                && otherJob.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, machine, startTime, owner, tags);
    }


}
