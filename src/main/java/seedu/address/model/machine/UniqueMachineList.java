package seedu.address.model.machine;

import seedu.address.model.machine.exceptions.DuplicateMachineException;
import seedu.address.model.machine.exceptions.MachineNotFoundException;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

/**
 * A list of machines that ensures uniqueness in Machine names
 */
public class UniqueMachineList {
    private final ArrayList<Machine> internalList = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent machineName
     */
    public boolean contains(Machine toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds the Machine to the list
     * The Machine must not exist in the list
     * @param toAdd
     */
    public void add(Machine toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMachineException();
        }

        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent machine from the list
     * The machine must exist in the list
     */
    public void remove(Machine toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MachineNotFoundException();
        }
    }




}
