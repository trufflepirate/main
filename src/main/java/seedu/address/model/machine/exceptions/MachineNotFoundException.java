package seedu.address.model.machine.exceptions;

import seedu.address.model.machine.Machine;

/**
 * Signals that the operation is unable to find the specific machine
 */
public class MachineNotFoundException extends RuntimeException{
    public MachineNotFoundException() {
        super("Operation would result in failed attempt to find specific machine");
    }

}
