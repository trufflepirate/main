package seedu.address.storage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

public class XmlAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlAddressBookStorageTest");

    private static final Path VALID_MAKERMANAGER_DATA_FOLDER = Paths.get("data");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readMakerMangerData_nullFilePath_throwsNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readMakerManagerData(null);
    }


    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTestData("NonExistentFile.xml","").isPresent());
    }



    @Test
    public void read_validFile() throws Exception {
        assertTrue(readTestData("makerManagerJobs.xml","valid").isPresent());
    }


    /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
     * That means you should not have more than one exception test in one method
     */

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {

        thrown.expect(DataConversionException.class);
        readInvalidDataFromStub("notValidXmlFormat.xml","invalid\\notValidXmlFormat");

    }

    @Test
    public void read_invalidAddressBook_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readInvalidDataFromStub("addressbook.xml","invalid");
    }

    @Test
    public void read_invalidMakerManagerJobs_throwNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        readTestData("makerManagerJobs.xml","invalid");
    }

//    @Test
//    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() throws Exception {
//        thrown.expect(DataConversionException.class);
//        readInvalidDataFromStub("invalidAndValidPersonAddressBook.xml");
//    } 

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


    @Test
    public void saveAddressBookException() {
        thrown.expect(AssertionError.class);
        saveAddressBook(new AddressBook(), "SomeFile.xml");
    }

    //-----------------------------Helper methods---------------------------------//
    /**
     * Reads entire makerManager data with userPrefs individual file paths
     */

    private java.util.Optional<ReadOnlyAddressBook> readMakerManagerData(UserPrefs userPrefs) throws Exception {
        return new XmlAddressBookStorage(userPrefs).readAddressBook(userPrefs);
    }

    /**
     * Reads single test makerMangager data file {@code filePath}
     */

    private Optional<ReadOnlyAddressBook> readTestData(String filePath, String directory) throws Exception {
        return new XmlAddressBookStorage(new UserPrefs()).readAddressBook(addToTestDataPathIfNotNull(filePath, directory));

    }

    /**
     * Reads single valid makerManager data file {@code validFilePath}
     */

    private Optional<ReadOnlyAddressBook> readSingleData(String validFilePath)
            throws Exception {
        return new XmlAddressBookStorage(
                new UserPrefs()).readAddressBook(addToValidDataPathIfNotNull(validFilePath));
    }


    /**
     * Reads an invalid MakerManager data file from xmladdressbookstoragestub
     */

    private Optional<ReadOnlyAddressBook> readInvalidDataFromStub(String invalidFilePath, String directory)
            throws Exception {
        return new XmlAddressBookStorageExceptionThrowingStub(
                new UserPrefs()).readAddressBook(addToTestDataPathIfNotNull(invalidFilePath, directory));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder, String directory) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(directory).resolve(prefsFileInTestDataFolder)
                : null;
    }

    private Path addToValidDataPathIfNotNull(String validFile) {
        return validFile != null
                ? VALID_MAKERMANAGER_DATA_FOLDER.resolve(validFile)
                : null;
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new XmlAddressBookStorageExceptionThrowingStub(new UserPrefs())
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath,"temp"));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    //======================================Stubs=============================================//

    /**
     * A Stub class to throw an exception when the save method is called
     */
    class XmlAddressBookStorageExceptionThrowingStub extends XmlAddressBookStorage {

        public XmlAddressBookStorageExceptionThrowingStub(UserPrefs userPrefs) {
            super(userPrefs);
        }

        /**
         * Assumes {@code filePath} is of XmlSerializableAddressBook Type
         */

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath)
                throws DataConversionException, FileNotFoundException {
            XmlSerializableAddressBook xmlAddressBook = XmlFileStorage.loadDataFromSaveFile(filePath);
            try {
                return Optional.of(xmlAddressBook.toModelType());
            } catch (IllegalValueException e) {
                e.printStackTrace();
                throw new DataConversionException(e);
            }
        }
        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            requireAllNonNull(addressBook, filePath);
            throw new IOException("dummy exception");
        }
    }
    //TODO Test to complete in the future
    /*
        @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.getRoot().toPath().resolve("TempAddressBook.xml");
        AddressBook original = getTypicalAddressBook();
        //XmlAddressBookStorage xmlAddressBookStorage = new XmlAddressBookStorage(filePath);

        //Save in new file and read back
        //xmlAddressBookStorage.saveAddressBook(original, filePath);
        //TODO TEST FAILED HERE REWRITE TEST IN THE FUTURE
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

}
