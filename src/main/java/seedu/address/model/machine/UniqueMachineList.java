package seedu.address.model.machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.job.Job;
import seedu.address.model.machine.exceptions.DuplicateMachineException;
import seedu.address.model.machine.exceptions.MachineNotFoundException;


/**
 * A list of machines that ensures uniqueness in Machine names
 */
public class UniqueMachineList {
    private static final Logger logger = LogsCenter.getLogger(UniqueMachineList.class);
    private final ObservableList<Machine> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent machine
     */
    public boolean contains(Machine toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMachine);
    }

    /**
     * Replaces the contents of this list with {@code machines}.
     * {@code machines} must not contain duplicate machines.
     */
    public void setMachines(List<Machine> machines) {
        requireNonNull(machines);
        if (!machinesAreUnique(machines)) {
            throw new DuplicateMachineException();
        }

        internalList.setAll(machines);
    }

    /**
     * Replaces the machine {@code target} in the list with {@code editedMachine}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedMachine} must not be the same as another existing person in the list.
     */
    public void setMachine(Machine target, Machine editedMachine) {
        requireAllNonNull(target, editedMachine);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MachineNotFoundException();
        }

        if (!target.isSameMachine(editedMachine) && contains(editedMachine)) {
            throw new DuplicateMachineException();
        }

        internalList.set(index, editedMachine);
    }

    /**
     * Returns the machine, given the machineName
     *
     * @param machineName
     * @return
     */
    public Machine findMachine(MachineName machineName) {
        for (Machine machine : internalList) {
            if (machine.getName().equals(machineName)) {
                return machine;
            }
        }
        return null;
    }

    /**
     * Adds a job the machine {@code target} jobs list
     */
    public void addJobToMachineList(Job job) throws MachineNotFoundException {
        requireAllNonNull(job);

        Machine target = this.findMachine(job.getMachineName());

        if (target == null) {
            throw new MachineNotFoundException();
        }

        target.addJob(job);
    }

    /**
     * Adds the Machine to the list
     * The Machine must not exist in the list
     *
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

    public Machine get(String machineName) {
        requireNonNull(machineName);

        logger.info("Doing for loop");
        for (Machine m : internalList) {
            logger.info(m.getName().fullName);
            if (m.getName().fullName.equals(machineName)) {
                logger.info("Machine name matches!!");
                Machine changedMachine = new Machine(m.getName(), m.getJobs(), m.getTags(), m.getStatus());
                return changedMachine;
            }
        }
        return null;
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Machine> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Returns true if {@code machines} contains only unique machines
     */
    private boolean machinesAreUnique(List<Machine> machines) {
        for (int i = 0; i < machines.size() - 1; i++) {
            for (int j = i + 1; j < machines.size(); j++) {
                if (machines.get(i).isSameMachine(machines.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    public Machine getMostFreeMachine() {
        float minimumTime = 999999;
        Machine mostFreeMachine = null;

        for (Machine machine : internalList) {
            if (machine.getTotalDuration() < minimumTime) {
                minimumTime = machine.getTotalDuration();
                mostFreeMachine = machine;
            }
        }

        return mostFreeMachine;
    }

}
