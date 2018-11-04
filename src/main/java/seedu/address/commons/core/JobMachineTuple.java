package seedu.address.commons.core;

import seedu.address.model.job.Job;
import seedu.address.model.machine.Machine;

/**
 * Represents a Tuple of Job Machines
 */
public class JobMachineTuple {
    public final Job job;
    public final Machine machine;

    public JobMachineTuple(Job job, Machine machine) {
        this.job = job;
        this.machine = machine;
    }

}
