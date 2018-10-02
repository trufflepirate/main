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

    //Identity field
    private Name name;
    private Machine machine;
    private TimeStamp time;
    private Person owner;

    //Data field
    private final Set<Tag> tags = new HashSet<>();
    private Set<Note> notes = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Job(Name name, Machine machine, Person owner, Set<Note> notes, Set<Tag> tags) {
        requireAllNonNull(name, machine, owner, notes, tags);
        this.name = name;
        this.machine = machine;
        this.owner = owner;
        this.tags.addAll(tags);
        this.notes.addAll(notes);

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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable note set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Note> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    public void addNote(Note newNote) {
        notes.add(newNote);
    }

    public void setName(String newName) {
        name = new Name(newName);
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
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
                && otherJob.getTags().equals(getTags())
                && otherJob.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, machine, time, owner, notes, tags);
    }


}
