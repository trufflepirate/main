package seedu.address.model.admin;

import seedu.address.model.person.Person;

import java.util.Objects;

/**
 * Represents an Admin in makerManager.
 */
public class Admin {
    private Username username;
    private PasswordHash passwordHash;

    public Admin (Username username, PasswordHash passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Username getUsername() {
        return username;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    /**
     * Returns true if both admins have the same identity and data fields.
     * This defines a stronger notion of equality between two admins.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Admin)) {
            return false;
        }

        Admin otherAdmin = (Admin) other;
        return otherAdmin.getUsername().equals(getUsername())
                && otherAdmin.getPasswordHash().equals(getPasswordHash());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(username, passwordHash);
    }
}
