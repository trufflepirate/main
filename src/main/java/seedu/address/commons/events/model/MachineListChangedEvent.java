package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/** Indicates the AddressBook in the model has changed */
public class MachineListChangedEvent extends BaseEvent {

    public final ReadOnlyAddressBook data;

    public MachineListChangedEvent(ReadOnlyAddressBook data) {

        // Sending only the relevant data to StorageManager
        AddressBook temp = new AddressBook();
        temp.setMachines(data.getMachineList());
        this.data = temp;
    }

    @Override
    public String toString() {
        return "number of machines " + data.getMachineList().size();
    }
}
