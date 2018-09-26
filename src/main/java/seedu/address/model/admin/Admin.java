package seedu.address.model.admin;

import java.util.Objects;

/**
 * Represents an Admin in makerManager.
 */
public class Admin {
    private Username username;
    private Password password;

    public Admin (Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
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
                && otherAdmin.getPassword().equals(getPassword());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(username, password);
    }
}
