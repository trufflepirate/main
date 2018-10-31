package seedu.address.model.machine;

/**
 * Represents a Machine Status.
 */
public enum MachineStatus {
    ENABLED,
    DISABLED,
    INVALID;


    public static boolean isValidMachineStatus(MachineStatus machineStatus) {
        return machineStatus.equals(MachineStatus.ENABLED) || machineStatus.equals(machineStatus.DISABLED);
    }
}
