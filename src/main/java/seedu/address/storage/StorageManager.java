package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.AdminListChangedEvent;
import seedu.address.commons.events.model.JobListChangedEvent;
import seedu.address.commons.events.model.MachineListChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private UserPrefs userPrefs;


    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.userPrefs = addressBookStorage.getUserPrefs();
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(userPrefs);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(UserPrefs userPrefs) throws DataConversionException,
                                                                                        IOException {
        return addressBookStorage.readAddressBook(userPrefs);
    }

    /**
     * Saves the entire addressbook to each of the relevant file
     * by marshalling the individual data correctly into its appropriate file
     * So far it has not been employed in the app yet
     * We have only been using saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath)
     * to save a data that is relevant to that file path
     * i.e saveAddressBook(addressBook, 'makerManagerMachines.xml') will save
     * the data accordingly but pulling the addressbook machines list and
     * saving it to the makerManagerMachines.xml but marshalling the data
     * through the format defined by XmlSerializableMakerManagerMachines.xml
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        //saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
        saveAddressBook(addressBook, userPrefs);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.info("Attempting to write to data file: " + filePath);
        logger.info("Attempting to write to a SINGLE data file");
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) throws IOException {
        logger.info("Attempting to write data for ALL files");
        addressBookStorage.saveAddressBook(addressBook, userPrefs);
    }

    /**
     * Should not be used here
     */
    @Override
    public UserPrefs getUserPrefs() {
        return addressBookStorage.getUserPrefs();
    }


    @Override
    @Subscribe
    public void handleAddressBookChangedEvent(AddressBookChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        logger.info("PersonsListChangedEvent triggered");
        try {
            saveAddressBook(event.data, userPrefs.getAddressBookFilePath());
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    @Override
    @Subscribe
    public void handleAdminListChangedEvent(AdminListChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        logger.info("AdminListChangedEvent triggered");
        try {
            saveAddressBook(event.data, userPrefs.getMakerManagerAdminsFilePath());
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    @Override
    @Subscribe
    public void handleMachineListChangedEvent(MachineListChangedEvent event) {
        try {
            logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
            logger.info("MachineListChangedEvent triggered");
            saveAddressBook(event.data, userPrefs.getMakerManagerMachinesFilePath());
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    @Override
    @Subscribe
    public void handleJobListChangedEvent(JobListChangedEvent event) {
        try {
            logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
            logger.info("JobsListChangedEvent triggered");
            saveAddressBook(event.data, userPrefs.getMakerManagerJobsFilePath());
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }
}
