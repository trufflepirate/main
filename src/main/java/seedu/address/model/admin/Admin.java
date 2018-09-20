package seedu.address.model.admin;

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

}
