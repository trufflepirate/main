package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.job.exceptions.DuplicateJobException;
import seedu.address.model.job.exceptions.JobNotFoundException;
import seedu.address.model.machine.exceptions.DuplicateMachineException;

import java.util.List;

/**
 * A list of Jobs whose elements are not repeated
 */
public class UniqueJobList {

    private final ObservableList<Job> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent job as the given argument.
     */
    public boolean contains(Job toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameJob);
    }

    /**
     * Adds a job to the list.
     * The jpb must not already exist in the list.
     */
    public void add(Job toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateJobException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent job from the list.
     * The job must exist in the list.
     */
    public void remove(Job toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new JobNotFoundException();
        }
    }

    /**
     * Replaces the job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the list.
     * The job identity of {@code editedJob} must not be the same as another existing job in the list.
     */
    public void updateJob(Job target, Job editedJob) {
        requireAllNonNull(target, editedJob);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new JobNotFoundException();
        }

        if (!target.isSameJob(editedJob) && contains(editedJob)) {
            throw new DuplicateJobException();
        }

        internalList.set(index, editedJob);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Job> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Returns true if the list has no repetition
     */
    public boolean isUnique() {
        boolean isUnique = true;

        for (Job flagJob : internalList) {
            for (Job pointerJob : internalList) {
                if (flagJob.isSameJob(pointerJob)) {
                    isUnique = false;
                }
            }
        }

        return isUnique;
    }

    /**
     *Returns the number of distinct elements in the list
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns Job
     * @param name
     */
    public Job findJob(JobName name) {
        for (Job job : internalList) {
            if (job.getName().equals(name)) {
                return job;
            }
        }
        return null;
    }

    public void setJobs(ObservableList<Job> jobs) {
        requireNonNull(jobs);
        if (!jobsAreUnique(jobs)) {
            throw new DuplicateMachineException();
        }

        internalList.setAll(jobs);
    }

    /**
     * Returns true if {@code jobs} contains only unique jobs
     */
    private boolean jobsAreUnique(List<Job> jobs) {
        for (int i = 0; i < jobs.size() - 1; i++) {
            for (int j = i + 1; j < jobs.size(); j++) {
                if (jobs.get(i).isSameJob(jobs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
