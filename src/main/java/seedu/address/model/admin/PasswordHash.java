package seedu.address.model.admin;

import seedu.address.model.person.Address;

/**
 * Represents the hash of the password of an admin
 */
public class PasswordHash {

    private String value;

    public PasswordHash(String hash) {
        this.value = hash;
    }

    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PasswordHash // instanceof handles nulls
                && value.equals(((PasswordHash) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
