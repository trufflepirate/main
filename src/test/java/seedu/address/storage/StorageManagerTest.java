package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.events.model.AdminListChangedEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.ui.testutil.EventsCollectorRule;

public class StorageManagerTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private StorageManager storageManager;

    private UserPrefs myTestUserPrefs;
    private Path testMakerManagerAdminsFilePath;
    private Path testMakerManagerJobsFilePath;
    private Path testMakerMangerMachinesFilePath;

    @Before
    public void setUp() {
        myTestUserPrefs = new UserPrefs();
        testMakerManagerAdminsFilePath = getTempFilePath("makerManagerAdmins.xml");
        testMakerManagerJobsFilePath = getTempFilePath("makerManagerJobs.xml");
        testMakerMangerMachinesFilePath = getTempFilePath("makerManagerMachines.xml");
        myTestUserPrefs.setMakerManagerAdminsFilePath(testMakerManagerAdminsFilePath);
        myTestUserPrefs.setMakerManagerJobsFilePath(testMakerManagerJobsFilePath);
        myTestUserPrefs.setMakerManagerMachinesFilePath(testMakerMangerMachinesFilePath);
        XmlAddressBookStorage addressBookStorage = new XmlAddressBookStorage(myTestUserPrefs);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);

    }

    private Path getTempFilePath(String fileName) {
        return testFolder.getRoot().toPath().resolve(fileName);
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(300, 600, 4, 6);
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void getUserPrefs() {
        assertNotNull(storageManager.getUserPrefs());
    }

    @Test
    public void getUserPrefsPath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void readEntireMakerManagerDataWithDefaultUserPrefs() throws IOException, DataConversionException {
        assertNotNull(storageManager.readAddressBook());
    }

    @Test
    public void readMakerManagerDataWithSingleFilePath () throws IOException, DataConversionException {
        Path tempPath = Paths.get("makerManagerJobs.xml");
        assertNotNull(storageManager.readAddressBook(tempPath));
    }

    @Test
    public void saveTestMakerManagerDataWithSingleFilePath () throws IOException, DataConversionException {
        storageManager.saveAddressBook(new AddressBook(), getTempFilePath("makerManagerJobs.xml"));
        assertNotNull(storageManager.readAddressBook(getTempFilePath("makerManagerJobs.xml")));
    }

    @Test
    public void saveTestEntireMakerManagerDataWithUserPrefs () throws IOException, DataConversionException {
        storageManager.saveAddressBook(new AddressBook(), myTestUserPrefs);
        assertNotNull(storageManager.readAddressBook(testMakerManagerAdminsFilePath));
        assertNotNull(storageManager.readAddressBook(testMakerManagerJobsFilePath));
        assertNotNull(storageManager.readAddressBook(testMakerMangerMachinesFilePath));
    }

    @Test
    public void testHandLeAdminListChangedEvent() {
        storageManager.handleAdminListChangedEvent(new AdminListChangedEvent(new AddressBook()));
        assertNotNull(testMakerManagerAdminsFilePath);
    }


}
