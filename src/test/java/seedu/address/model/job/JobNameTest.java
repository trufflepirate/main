package seedu.address.model.job;

import org.junit.Test;

import seedu.address.testutil.Assert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JobNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new JobName(null));
    }

    @Test
    public void constructor_invalidJobName_throwsIllegalArgumentException() {
        String invalidJobName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new JobName(invalidJobName));
    }

    @Test
    public void isValidJobName() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> JobName.isValidJobName(null));

        // invalid name
        assertFalse(JobName.isValidJobName("")); // empty string
        assertFalse(JobName.isValidJobName(" ")); // spaces only
        assertFalse(JobName.isValidJobName("^")); // only non-alphanumeric characters
        assertFalse(JobName.isValidJobName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(JobName.isValidJobName("peter jack")); // alphabets only
        assertTrue(JobName.isValidJobName("12345")); // numbers only
        assertTrue(JobName.isValidJobName("peter the 2nd")); // alphanumeric characters
        assertTrue(JobName.isValidJobName("Capital Tan")); // with capital letters
        assertTrue(JobName.isValidJobName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
