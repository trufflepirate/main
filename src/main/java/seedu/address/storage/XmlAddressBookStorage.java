package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

import seedu.address.model.admin.Admin;
import seedu.address.model.admin.Password;
import seedu.address.model.admin.Username;
import seedu.address.storage.admin.XmlSerializableMakerManagerAdmins;
import seedu.address.storage.job.XmlSerializableMakerManagerJobs;
import seedu.address.storage.machine.XmlSerializableMakerManagerMachines;

/**
 * A class to access AddressBook data stored as multiple xml files on the hard disk.
 */
public class XmlAddressBookStorage extends ComponentManager implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlAddressBookStorage.class);

    private Path filePath;
    private UserPrefs userPrefs;

    public XmlAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }
    public XmlAddressBookStorage(UserPrefs userPrefs) {
        this.userPrefs = userPrefs;
        this.filePath = userPrefs.getAddressBookFilePath();
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(userPrefs);
    }

    /**
     * Similar to {@link #readAddressBook()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     * Returns a single file addressbook that only consists of data from one xml file
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException,
                                                                                 FileNotFoundException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("AddressBook file "  + filePath + " not found");
            return Optional.empty();
        }

        XmlSerializableAddressBook xmlAddressBook = XmlFileStorage.loadDataFromSaveFile(filePath);
        try {
            return Optional.of(xmlAddressBook.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Returns the full in-memory addressbook that contains all the data from the xml files
     * i.e addressbook will contain list of persons,admins,machines etc..
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(UserPrefs userPrefs) throws DataConversionException,
                                                                                        FileNotFoundException {
        requireNonNull(userPrefs);

        Path mainAddressBookFile = userPrefs.getAddressBookFilePath();
        Path makerManagerMachinesFile = userPrefs.getMakerManagerMachinesFilePath();
        Path makerManagerAdminsFile = userPrefs.getMakerManagerAdminsFilePath();
        Path makerManagerJobsFile = userPrefs.getMakerManagerJobsFilePath();

        try {
            if (!Files.exists(mainAddressBookFile)) {
                logger.info("AddressBook file " + mainAddressBookFile + " not found");
                logger.info("Creating new " + mainAddressBookFile);
                FileUtil.createIfMissing(mainAddressBookFile);
            }

            if (!Files.exists(makerManagerMachinesFile)) {
                logger.info("AddressBook file "  + makerManagerMachinesFile + " not found");
                logger.info("Creating new " + makerManagerMachinesFile);
                FileUtil.createIfMissing(makerManagerMachinesFile);
            }

            if (!Files.exists(makerManagerAdminsFile)) {
                logger.info("AddressBook file "  + makerManagerAdminsFile + " not found");
                logger.info("Creating new " + makerManagerAdminsFile);
                FileUtil.createIfMissing(makerManagerAdminsFile);
            }

            if (!Files.exists(makerManagerJobsFile)) {
                logger.info("AddressBook file " + makerManagerJobsFile + " not found");
                logger.info("Creating new " + makerManagerJobsFile);
                FileUtil.createIfMissing(makerManagerJobsFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        AddressBook fullAddressBookData = new AddressBook();

        /**
         * Loads all the data into each individual temporary serializable xml address book
         * by parsing the data accordingly through the correct marshalling format stated in XmlFileStorage
         * Will throw dataConversionError when the file is empty
         * or if incorrect format
         */

        try {
            XmlSerializableAddressBook xmlAddressBook = XmlFileStorage.loadDataFromSaveFile(mainAddressBookFile);
            AddressBook mainAddressBookData = xmlAddressBook.toModelType();
            fullAddressBookData.setPersons(mainAddressBookData.getPersonList());
        } catch (DataConversionException dce) {
            logger.info("Person conversion error");
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        try {
            XmlSerializableMakerManagerMachines xmlMakerManagerMachines =
                    XmlFileStorage.loadMakerManagerMachineDataFromSaveFile(makerManagerMachinesFile);
            AddressBook machinesAddressBookData = xmlMakerManagerMachines.toModelType();
            fullAddressBookData.setMachines(machinesAddressBookData.getMachineList());
        } catch (DataConversionException dce) {
            logger.info("Machine conversion error");
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        try {
            XmlSerializableMakerManagerJobs xmlMakerManagerJobs =
                    XmlFileStorage.loadMakerManagerJobDataFromSaveFile(makerManagerJobsFile);
            AddressBook jobsAddressBookData = xmlMakerManagerJobs.toModelType();
            fullAddressBookData.setJobs(jobsAddressBookData.getJobList());
            logger.info("Full addressbook data jobs size : " + fullAddressBookData.getJobList().size());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        } catch (DataConversionException dce) {
            logger.info("Jobs conversion error");
        }

        try {
            XmlSerializableMakerManagerAdmins xmlMakerManagerAdmins =
                    XmlFileStorage.loadMakerManagerAdminDataFromSaveFile(makerManagerAdminsFile);
            AddressBook adminsData = xmlMakerManagerAdmins.toModelType();
            fullAddressBookData.setAdmins(adminsData.getAdminList());

        } catch (DataConversionException dce) {
            logger.info("Admins conversion error");
            Username username = new Username("admin");
            Password password = new Password("admin");
            Admin admin = new Admin(username, password);
            AddressBook newAdminData = new AddressBook();
            newAdminData.addAdmin(admin);
            XmlFileStorage.saveDataToFile(userPrefs.getMakerManagerAdminsFilePath(),
                    new XmlSerializableMakerManagerAdmins(newAdminData));
            logger.info("Creating new admin file");
            fullAddressBookData.setAdmins(newAdminData.getAdminList());

        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        try {
            XmlSerializableMakerManagerJobs xmlMakerManagerJobs =
                    XmlFileStorage.loadMakerManagerJobDataFromSaveFile(makerManagerJobsFile);
            AddressBook jobsAddressBookData = xmlMakerManagerJobs.toModelType();
            fullAddressBookData.setJobs(jobsAddressBookData.getJobList());
        } catch (DataConversionException dce) {
            logger.info("Job conversion error");
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }


        return Optional.of(fullAddressBookData);


    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}
     * @param filePath location of the data. Cannot be null
     * Saves the data to a single file depending on the file path
     * specified
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);

        switch (filePath.toString()) {
        case "data\\addressbook.xml" :
            XmlFileStorage.saveDataToFile(filePath, new XmlSerializableAddressBook(addressBook));
            break;
        case "data\\makerManagerMachines.xml" :
            XmlFileStorage.saveDataToFile(filePath, new XmlSerializableMakerManagerMachines(addressBook));
            break;
        case "data\\makerManagerAdmins.xml" :
            XmlFileStorage.saveDataToFile(filePath, new XmlSerializableMakerManagerAdmins(addressBook));
            break;
        case "data\\makerManagerJobs.xml" :
            XmlFileStorage.saveDataToFile(filePath, new XmlSerializableMakerManagerJobs(addressBook));
            break;
        default:
            logger.info("No such file path available to save data in");
        }

    }

    /**
     * Saves all the data at once to all the files available
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(userPrefs);

        Path mainAddressBookFile = userPrefs.getAddressBookFilePath();
        Path makerManagerMachinesFile = userPrefs.getMakerManagerMachinesFilePath();
        Path makerManagerAdminsFile = userPrefs.getMakerManagerAdminsFilePath();
        Path makerManagerJobsFile = userPrefs.getMakerManagerJobsFilePath();

        FileUtil.createIfMissing(mainAddressBookFile);
        FileUtil.createIfMissing(makerManagerMachinesFile);
        FileUtil.createIfMissing(makerManagerAdminsFile);
        FileUtil.createIfMissing(makerManagerJobsFile);

        /**
         * The serializable all has to be different classes as the formatting
         * for each xml document is different since we saving to different files
         * and not just one file
         */
        XmlFileStorage.saveDataToFile(mainAddressBookFile, new XmlSerializableAddressBook(addressBook));
        XmlFileStorage.saveDataToFile(makerManagerMachinesFile, new XmlSerializableMakerManagerMachines(addressBook));
        XmlFileStorage.saveDataToFile(makerManagerAdminsFile, new XmlSerializableMakerManagerAdmins(addressBook));
        XmlFileStorage.saveDataToFile(makerManagerJobsFile, new XmlSerializableMakerManagerJobs(addressBook));

    }

    @Override
    public UserPrefs getUserPrefs() {
        return userPrefs;
    }

}
