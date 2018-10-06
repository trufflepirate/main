package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/** Indicates the AddressBook in the model has changed  */
public class JobListChangedEvent extends BaseEvent {

    public final ReadOnlyAddressBook data;

    public JobListChangedEvent(ReadOnlyAddressBook data) {

        // Sending only the relevant data to StorageManager
        AddressBook temp = new AddressBook();
        temp.setJobs(data.getJobList());
        this.data = temp;
    }

    @Override
    public String toString() {
        return "number of jobs " + data.getJobList().size();
    }
}
