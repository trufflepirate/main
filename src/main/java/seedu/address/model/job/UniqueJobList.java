package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ModelManager;
import seedu.address.model.job.exceptions.DuplicateJobException;
import seedu.address.model.job.exceptions.JobNotFoundException;

/**
 * A list of Jobs whose elements are not repeated
 */
public class UniqueJobList {

    private static final Logger logger = LogsCenter.getLogger(UniqueJobList.class);
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

    public void setJobs(UniqueJobList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code jobs}.
     * {@code jobs} must not contain duplicate jobs.
     */
    public void setJobs(List<Job> jobs) {
        requireAllNonNull(jobs);
        if (!jobsAreUnique(jobs)) {
            throw new DuplicateJobException();
        }

        internalList.setAll(jobs);
    }

    /**
     * Returns a job by name
     */

    public Job get(String jobName) {
        requireNonNull(jobName);
        logger.info("Jobs size : " + Integer.toString(internalList.size()));
        logger.info("Doing for loop for jobs");
        for (Job j : internalList) {
            logger.info(j.getJobName().fullName);
            if (j.getJobName().fullName.equals(jobName)) {
                logger.info("Job name matches!!");
                Job changedJob = new Job(j.getJobName(),
                                        j.getMachine(),
                                        j.getOwner(),
                                        j.getPriority(),
                                        j.getJobNote(),
                                        j.getTags());
                return changedJob;
            }
        }
        return null;
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
    public boolean jobsAreUnique(List<Job> jobs) {
        for (int i = 0; i < jobs.size() - 1; i++) {
            for (int j = i + 1; j < jobs.size(); j++) {
                if (jobs.get(i).isSameJob(jobs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *Returns the number of distinct elements in the list
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns the job, given the jobName
     * @param jobName
     * @return
     */
    public Job findJob(JobName jobName) {
        for (Job job: internalList) {
            if (job.getJobName().equals(jobName)) {
                return job;
            }
        }
        return null;
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedJob} must not be the same as another existing person in the list.
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
}
