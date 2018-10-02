package seedu.address.model.job;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;
import seedu.address.model.tag.Tag;

/**
 * Represents a Machine assigned with the job. Inherit from Machine.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class JobMachine extends Machine {

    /**
     * Construct a {@code JobMachine}
     *
     * @param name A valid name as declared in the super class
     * @param jobs A seris of Jobs that the machine will do
     * @param tags Special remarks to the machine
     * @param status The operating status of the machine
     *
     * Every field must be present and not null.
     */
    public JobMachine(MachineName name, ArrayList<Job> jobs, Set< Tag > tags, boolean status) {
        super(name, jobs, tags, status);
    }

}
