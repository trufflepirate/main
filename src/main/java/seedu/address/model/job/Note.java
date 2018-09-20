package seedu.address.model.job;

/**
 * Represents a Note Object for Job.
 */
public class Note {

    private String value;

    public Note(String note) {
        this.value = note;
    }

    public String toString() {
        return value;
    }

    public void changeNote(String newNote) {
        this.value = newNote;
    }

}
