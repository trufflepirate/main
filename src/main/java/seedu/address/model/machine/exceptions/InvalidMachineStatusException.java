package seedu.address.model.machine.exceptions;

/**
 * Signals that the operation is setting an invalid machine status
 */
public class InvalidMachineStatusException extends RuntimeException {
    public InvalidMachineStatusException() {
        super("Operation would result in a failed attempt to set machine status");
    }
}
