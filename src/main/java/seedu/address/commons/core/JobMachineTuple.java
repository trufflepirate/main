package seedu.address.commons.core;

import seedu.address.model.job.Job;
import seedu.address.model.machine.Machine;

public class JobMachineTuple {
    final public Job job;
    final public Machine machine;

    public JobMachineTuple(Job job, Machine machine) {
        this.job = job;
        this.machine = machine;
    }

}
