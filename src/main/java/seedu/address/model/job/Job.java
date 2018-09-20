package seedu.address.model.job;

import seedu.address.model.person.Machine;
import seedu.address.model.person.Name;

/**
 * Represents a Printing job in makerManager
 */
public class Job {
    private Name name;
    private Machine machine;
    private Note note = new Note("");
    private TimeStamp time = new TimeStamp();

    public Job(String name, Machine machine) {
        this.name = new Name(name);
        this.machine = machine;
    }

    public Name getName() {
        return name;
    }

    public Machine getMachine() {
        return machine;
    }

    public long getTime() {
        return time.getTime();
    }

    public void changeName(String name) {
        this.name = new Name(name);
    }

    public void changeMachine(Machine machine) {
        this.machine = machine;
    }

    /*public void changeTime(long time) {
        this.time.setTime(time);
    }*/
}
