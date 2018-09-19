package seedu.address.model.admin;

/**
 * Represents the hash of the password of an admin
 */
public class PasswordHash {

    private String value;

    public PasswordHash(String hash) {
        this.value = hash;
    }

    public String toString() { return value; }

}
