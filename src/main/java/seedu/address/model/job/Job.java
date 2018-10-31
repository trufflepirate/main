package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.job.Status.ONGOING;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.machine.Machine;
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

    public static final String MEEEAGE_PRIORITY_CONSTRAINTS =
            "Job priority can only be URGENT, HIGH and NORMAL";

    private static final Logger logger = LogsCenter.getLogger(Job.class);

    //Identity field
    private JobName name;
    private Machine machine;
    private TimeStamp startTime;
    private Person owner;
    private final String addedTime;

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
    public Job(JobName name, Machine machine, Person owner, Priority priority, float duration,
               JobNote jobNote, Set<Tag> tags) {
        requireAllNonNull(name, machine, owner, tags);
        this.name = name;
        this.machine = machine;
        this.owner = owner;
        this.priority = priority;
        this.duration = duration;
        this.jobNote = jobNote;
        this.tags.addAll(tags);

        this.status = Status.QUEUED;
        startTime = new TimeStamp();
        addedTime = new TimeStamp().showTime();
    }

    /**
     * Recovers a job object from the storage file
     */
    public Job(JobName name, Machine machine, Person owner, String addedTime, TimeStamp startTime, Priority priority,
               Status status, float duration, JobNote jobNote, Set<Tag> tags) {
        requireAllNonNull(name, machine, owner, tags);
        this.name = name;
        this.machine = machine;
        this.owner = owner;
        this.addedTime = addedTime;
        this.priority = priority;
        this.status = status;
        this.duration = duration;
        this.jobNote = jobNote;
        this.startTime = startTime;
        this.tags.addAll(tags);
    }

    /**
     * returns the job note
     */
    public JobNote getJobNote() {
        return this.jobNote;
    }


    /**
     * returns the job duration
     */
    public float getDuration() {
        return this.duration;
    }


    /**
     * set the duration to the input new one
     * @param duration
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * starts a job
     */
    public void startJob() {
        this.status = ONGOING;
        this.startTime = new TimeStamp();
    }

    /**
     * restarts a job once the job is cancelled
     */
    public void restartJob() {
        this.startJob();
    }


    /**
     * cancel the printing of the job
     */
    public void cancelJob() {
        this.status = Status.CANCELLED;
    }

    /**
     * finishes a job
     */
    public void finishJob() {
        this.status = Status.FINISHED;
    }

    /**
     * sets the note of the job by a new job
     * @param jobNote
     */
    public void setJobNote(String jobNote) {
        this.jobNote.changeNote(jobNote);
    }

    /**
     * returns the priority of the job
     * @return
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * sets the priority of the job
     * @param priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * returns the status of the job
     * @return
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * sets the status of the job
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }


    /**
     * returns the name of the job
     * @return
     */
    public JobName getJobName() {
        return name;
    }


    /**
     * returns the machine object of the job
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     * returns the time the job is added
     */
    public String getAddedTime() {
        return addedTime;
    }

    /**
     * returns the timestamp of the job being executed
     */
    public TimeStamp getStartTime() {
        return startTime;
    }

    /**
     * returns the owner object of the job
     */
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

    /**
     * Adds an extra note to the job
     */
    public void addNote(String addition) {
        this.jobNote.addNote(addition);
    }

    /**
     * changes the name of the job to a new name
     * @param newName
     */
    public void setName(String newName) {
        name = new JobName(newName);
    }

    /**
     * assigns a new machine to the job
     * @param newMachine
     */
    public void setMachine(Machine newMachine) {
        machine = newMachine;
    }

    /**
     * changes the owner of the job to a new one
     * @param newOwner
     */
    public void setOwner(Person newOwner) {
        owner = newOwner;
    }


    /**
     * checks if a job has been finished
     */
    /*
    public boolean isFinished() throws JobNotStartedException {

        if (this.status == ONGOING) {
            Integer[] current = new TimeStamp().getTime();
            Integer[] start = startTime.getTime();
            Integer[] deviation = new Integer[start.length];

            for (int i = 0; i < start.length; i++) {
                deviation[i] = current[i] - start[i];
            }

            double runningTime = 30.0 * 24.0 * deviation[0] + 24.0 * deviation[1] + deviation[2]
                    + 1 / 60 * deviation[3] + 1 / 3600 * deviation[4];

            return runningTime > this.duration;
        } else {
            throw new JobNotStartedException();
        }
    }
    */


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
                && otherJob.getMachine().equals(getMachine())
                && (otherJob.getDuration() == getDuration())
                && otherJob.getOwner().equals(getOwner());
    }

    /**
     * Compares priority between two jobs
     */

    public int hasHigherPriority(Job comparedJob) {
        //TODO clean up code to make it neater for comparison
        if (this.equals(comparedJob)) {
            return 0;
        }

        if (Priority.isHigherPriority(this.getPriority(), comparedJob.getPriority()) != 0) {
            return Priority.isHigherPriority(this.getPriority(), comparedJob.getPriority());
        }
        if (TimeStamp.compareTime(this.addedTime, comparedJob.addedTime)) {
            return 1;
        }
        if (this.getJobName().fullName.compareTo(comparedJob.getJobName().fullName) <= 0) {
            return 1;
        }

        return -1;

    }

    /**
     * Returns true if both jobs have the same identity and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if(! (other instanceof Job)) return false;

        Job otherJob = (Job) other;

        if (otherJob == this) {
            return true;
        }

        if (otherJob == null) {
            return false;
        }

        return otherJob.getJobName().equals(this.getJobName())
                && otherJob.getMachine().getName().equals(this.getMachine().getName())
                && otherJob.getOwner().getName().equals(this.getOwner().getName())
                && otherJob.getAddedTime().equals(this.getAddedTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, machine, startTime, owner, tags);
    }

    @Override
    public String toString() {
        return "Job name " + this.getJobName().fullName
                + "\nJob machine " + this.getMachine()
                + "\nJob Priority " + this.getPriority()
                + "\nJob status " + this.getStatus();
    }


}
