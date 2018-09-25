package seedu.address.model.admin.exceptions;

/**
 * Signals that the operation will result in duplicate Admin (Admins are considered duplicates if they have the same
 * username).
 */
public class DuplicateAdminException extends RuntimeException {
    public DuplicateAdminException() {
        super("Operation would result in duplicate persons");
    }
}