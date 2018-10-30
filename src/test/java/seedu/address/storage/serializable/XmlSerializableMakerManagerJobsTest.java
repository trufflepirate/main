package seedu.address.storage.serializable;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.AddressBook;
import seedu.address.storage.job.XmlSerializableMakerManagerJobs;
import seedu.address.testutil.testdata.ValidJobs;

public class XmlSerializableMakerManagerJobsTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "XmlSerializableMakerManagerJobsTest");
    private static final Path VALID_JOBS_FILE = TEST_DATA_FOLDER.resolve("validJobsFile.xml");
    private static final Path INVALID_JOBS_FILE = TEST_DATA_FOLDER.resolve("invalidJobsFile.xml");
    private static final Path DUPLICATE_JOBS_FILE = TEST_DATA_FOLDER.resolve("duplicateJobsFile.xml");


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /*
    @Test
    public void toModelType_validJobsFile_success() throws Exception {
        XmlSerializableMakerManagerJobs dataFromFile =
                XmlUtil.getDataFromFile(VALID_JOBS_FILE, XmlSerializableMakerManagerJobs.class);
        AddressBook makerManagerJobsDataFromFile = dataFromFile.toModelType();
        AddressBook validMakerManagerJobs = ValidJobs.getValidMakerManagerJobsData();
        assertEquals(makerManagerJobsDataFromFile, validMakerManagerJobs);
    }
    */

    @Test
    public void toModelType_invalidJobsFile_throwsNullPointerException() throws Exception {
        XmlSerializableMakerManagerJobs dataFromFile =
                XmlUtil.getDataFromFile(INVALID_JOBS_FILE, XmlSerializableMakerManagerJobs.class);
        thrown.expect(NullPointerException.class);
        dataFromFile.toModelType();
    }

    /*
    @Test
    public void toModelType_duplicateJobs_throwsIllegalValueException() throws Exception {
        XmlSerializableMakerManagerJobs dataFromFile =
                XmlUtil.getDataFromFile(DUPLICATE_JOBS_FILE, XmlSerializableMakerManagerJobs.class);
        thrown.expect(IllegalValueException.class);
        thrown.expectMessage(XmlSerializableMakerManagerJobs.MESSAGE_DUPLICATE_JOB);
        dataFromFile.toModelType();
    }
    */

}


