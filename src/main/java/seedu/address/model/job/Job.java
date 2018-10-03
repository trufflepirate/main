package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.machine.Machine;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a Printing job in MakerManager.
 * Morphed from the Person class in original Addressbook
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    //Identity field
    private JobName name;
    private Machine machine;
    private TimeStamp time;
    private Person owner;

    //Data field
    private JobNote note;

    /**
     * Every field must be present and not null.
     */
    public Job(Name name, Machine machine, Person owner, JobNote notes) {
        requireAllNonNull(name, machine, owner, notes);
        this.name = (JobName) name;
        this.machine = machine;
        this.owner = owner;
        this.note = note;

        time = new TimeStamp();
    }

    public Name getName() {
        return name;
    }

    public Machine getMachine() {
        return machine;
    }

    public TimeStamp getTime() {
        return time;
    }

    public Person getOwner() {
        return owner;
    }

    /**
     * Returns an immutable note set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public JobNote getNotes() {
        return note;
    }

    /*
    * In case the function of adding new notes during the process in needed
    * have to change the note to a set
    public void addNote(JobNote newNote) {
        notes.add(newNote);
    }
    */

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
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getName().equals(getName())
                && (otherJob.getMachine().equals(getMachine())
                || otherJob.getTime().equals(getTime())
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
        return otherJob.getName().equals(getName())
                && otherJob.getMachine().equals(getMachine())
                && otherJob.getOwner().equals(getOwner())
                && otherJob.getTime().equals(getTime())
                && otherJob.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, machine, time, owner, note);
    }


}
