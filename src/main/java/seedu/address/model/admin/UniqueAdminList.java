package seedu.address.model.admin;

import java.util.ArrayList;

import seedu.address.model.admin.exceptions.AdminNotFoundException;
import seedu.address.model.admin.exceptions.DuplicateAdminException;
import static java.util.Objects.requireNonNull;

/**
 * A list of admins that ensures uniqueness in Usernames
 */
public class UniqueAdminList {
    private final ArrayList<Admin> internalList = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent userName
     */
    public boolean contains(Admin toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public void add(Admin toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAdminException();
        }

        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent admin from the list.
     * The admin must exist in the list.
     */
    public void remove(Admin toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AdminNotFoundException();
        }
    }

}
