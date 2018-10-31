package seedu.address.storage;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

public class XmlAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlAddressBookStorageTest");
    private static final Path MAKERMANAGER_DATA_FOLDER = Paths.get("data");

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
     * That means you should not have more than one exception test in one method
     */

    @Test
    public void readMakerMangerData_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readMakerManagerData(null);
    }

    //================================ Reading makerManager test files ===============================//

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readData("NonExistentFile.xml", "",  true).isPresent());
    }

    @Test
    public void read_makerManagerTestFile() throws Exception {
        //assertTrue(readData("makerManagerJobs.xml", "valid" , true).isPresent());
    }

    /*
    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {
        thrown.expect(DataConversionException.class);
        readData("addressbook.xml", "invalid\\notValidXmlFormat", true);
    }
    */

    /*
    @Test
    public void read_invalidAddressBook_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readData("addressbook.xml", "invalid", true);
    }
    */

    /*
    @Test
    public void read_invalidMakerManagerJobs_throwNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readData("makerManagerJobs.xml", "invalid", true);
    }
    */


    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveAddressBook(null, "SomeFile.xml");
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveAddressBook(new AddressBook(), null);
    }


    //-----------------------------Helper methods---------------------------------//
    /**
     * Reads entire makerManager data with userPrefs individual file paths
     */

    private java.util.Optional<ReadOnlyAddressBook> readMakerManagerData(UserPrefs userPrefs) throws Exception {
        return new XmlAddressBookStorage(userPrefs).readAddressBook(userPrefs);
    }

    /**
     * Reads a single real/test makerManager Data file {@code filePath}
     */

    private Optional<ReadOnlyAddressBook> readData(String filePath, String directory, Boolean isTestData)
            throws Exception {
        return new XmlAddressBookStorage(new UserPrefs()).readAddressBook(getFilePath(filePath, directory, isTestData));
    }


    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new XmlAddressBookStorage(new UserPrefs())
                    .saveAddressBook(addressBook, testFolder.getRoot().toPath().resolve(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
    /**
     * Helper method to get the correct file paths for both real and test data
     */
    private Path getFilePath(String prefsFileInTestDataFolder, String directory, Boolean isTestData) {
        if (prefsFileInTestDataFolder != null) {
            if (isTestData) {
                return TEST_DATA_FOLDER.resolve(directory).resolve(prefsFileInTestDataFolder);
            } else {
                return MAKERMANAGER_DATA_FOLDER.resolve(prefsFileInTestDataFolder);
            }
        }
        return null;
    }


    //======================================Stubs=============================================//

    //TODO Write more complete tests


    //TODO Tests to complete in the future

    /*
        @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempAddressBook.xml");
        AddressBook original = getTypicalAddressBook();
        //XmlAddressBookStorage xmlAddressBookStorage = new XmlAddressBookStorage(filePath);

        //Save in new file and read back
        //xmlAddressBookStorage.saveAddressBook(original, filePath);
        /*
        ReadOnlyAddressBook readBack = xmlAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        //Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        xmlAddressBookStorage.saveAddressBook(original, filePath);
        readBack = xmlAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        //Save and read without specifying file path
        original.addPerson(IDA);
        xmlAddressBookStorage.saveAddressBook(original); //file path not specified
        readBack = xmlAddressBookStorage.readAddressBook().get(); //file path not specified
        assertEquals(original, new AddressBook(readBack));
        */
    /*
        @Test
        public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() throws Exception {
            thrown.expect(DataConversionException.class);
            readInvalidDataFromStub("invalidAndValidPersonAddressBook.xml");
            }

        */

}
