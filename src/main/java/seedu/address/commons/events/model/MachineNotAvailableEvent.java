package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

/**
 * Raises an event to inform a user that the machine is not available
 */
public class MachineNotAvailableEvent extends BaseEvent {


    @Override
    public String toString() {
        return "Machine not available";
    }
}
