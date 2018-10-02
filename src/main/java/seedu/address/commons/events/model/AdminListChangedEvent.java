package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyAddressBook;

/** Indicates the AddressBook in the model has changed*/
public class AdminListChangedEvent extends BaseEvent {

    public final ReadOnlyAddressBook data;

    public AdminListChangedEvent(ReadOnlyAddressBook data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of admins " + data.getAdminList().size();
    }
}
