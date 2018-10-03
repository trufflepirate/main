package seedu.address.model.machine.exceptions;

/**
 * Signals that the operation will result in duplicate Machine
 * Machines are considered duplicates if they have the same machineName
 */

public class DuplicateMachineException extends RuntimeException {
    public DuplicateMachineException() {
        super("Operation would result in duplicate machine");
    }
}
