package seedu.address.model.job;

import seedu.address.model.person.Name;

/**
 * Represent the name of a job
 * The class have the similar functionality as the Name class of Person and inherent from that
 */
public class JobName extends Name {

    /**
     * Construct a {@code JobName}.
     *
     * @param name A valid name as declared in the super class.
     *
     * Every field must be present and not null.
     */
    public JobName(String name) {
        super(name);
    }

}
