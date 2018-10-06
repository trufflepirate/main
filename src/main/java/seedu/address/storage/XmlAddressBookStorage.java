package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

import seedu.address.model.machine.Machine;
import seedu.address.model.person.Person;
import seedu.address.storage.admin.XmlSerializableMakerManagerAdmins;
import seedu.address.storage.machine.XmlSerializableMakerManagerMachines;

/**
 * A class to access AddressBook data stored as an xml file on the hard disk.
 */
public class XmlAddressBookStorage implements AddressBookStorage {

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
        logger.info("Reading address book in XmlAddressBookStorage class");
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
        logger.info("Filepath in XmlAddressBookStorage class : " + filePath.toString());

        XmlSerializableAddressBook xmlAddressBook = XmlFileStorage.loadDataFromSaveFile(filePath);
        try {
            return Optional.of(xmlAddressBook.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Returns the full in memory addressbook that contains all the data from the xml files
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(UserPrefs userPrefs) throws DataConversionException,
                                                                                        FileNotFoundException {
        requireNonNull(userPrefs);
        logger.info("Debugging user preferences");

        Path mainAddressBookFile = userPrefs.getAddressBookFilePath();
        Path makerManagerMachinesFile = userPrefs.getMakerManagerMachinesFilePath();
        Path makerManagerAdminsFile = userPrefs.getMakerManagerAdminsFilePath();

        if (!Files.exists(mainAddressBookFile)) {
            logger.info("AddressBook file "  + filePath + " not found");
            return Optional.empty();
        }

        if (!Files.exists(makerManagerMachinesFile)) {
            logger.info("AddressBook file "  + filePath + " not found");
            return Optional.empty();
        }

        if (!Files.exists(makerManagerAdminsFile)) {
            logger.info("AddressBook file "  + filePath + " not found");
            return Optional.empty();
        }

        logger.info("Printing data from xml files 1 ");
        XmlSerializableAddressBook xmlAddressBook = XmlFileStorage.loadDataFromSaveFile(mainAddressBookFile);
        logger.info("Printing data from xml files 2");
        XmlSerializableMakerManagerMachines xmlMakerManagerMachinesAddressBook =
                XmlFileStorage.loadMakerManagerDataFromSaveFile(makerManagerMachinesFile);


        try {


            AddressBook mainAddressBookData = xmlAddressBook.toModelType();
            AddressBook machinesAddressBookData = xmlMakerManagerMachinesAddressBook.toModelType();
            //AddressBook adminsAddressBookData = xmlMakerManagerAdminsAddressBook.toModelType();


            logger.info(Integer.toString(mainAddressBookData.getPersonList().size()));
            for (Person person : machinesAddressBookData.getPersonList()) {
                logger.info("Person full name : " + person.getName());
            }
            logger.info(Integer.toString(machinesAddressBookData.getMachineList().size()));
            for (Machine machine : machinesAddressBookData.getMachineList()) {
                logger.info("Machine full name : " + machine.getName().fullName);
            }
            AddressBook fullAddressBookData = new AddressBook();
            fullAddressBookData.setPersons(mainAddressBookData.getPersonList());
            fullAddressBookData.setMachines(machinesAddressBookData.getMachineList());
            //fullAddressBookData.setAdmins(adminsAddressBookData.getAdminList());
            logger.info("DISPLAYING NEW FULL ADDRESSBOOK DATA");
            logger.info(fullAddressBookData.toString());
            return Optional.of(fullAddressBookData);

        } catch (IllegalValueException ive) {
            ive.printStackTrace();
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }


    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        logger.info("Printing out filePath to check for switch statement");
        logger.info(filePath.toString());

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
        default:
            logger.info("No such file path available to save data in");
        }

        //TODO: @TY make XmlSerializableMakerManagerJobs and Relevant classes

    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(userPrefs);

        Path mainAddressBookFile = userPrefs.getAddressBookFilePath();
        Path makerManagerMachinesFile = userPrefs.getMakerManagerMachinesFilePath();
        Path makerManagerAdminsFile = userPrefs.getMakerManagerAdminsFilePath();

        //Debugging
        logger.info(mainAddressBookFile.toString());
        logger.info(makerManagerMachinesFile.toString());
        logger.info(makerManagerAdminsFile.toString());

        FileUtil.createIfMissing(mainAddressBookFile);
        FileUtil.createIfMissing(makerManagerMachinesFile);
        FileUtil.createIfMissing(makerManagerAdminsFile);

        /**
         * The serializable all has to be different classes as the formatting
         * for each xml document is different since we saving to different files
         * and not just one file
         */
        XmlFileStorage.saveDataToFile(mainAddressBookFile, new XmlSerializableAddressBook(addressBook));
        XmlFileStorage.saveDataToFile(makerManagerMachinesFile, new XmlSerializableMakerManagerMachines(addressBook));
        XmlFileStorage.saveDataToFile(makerManagerAdminsFile, new XmlSerializableMakerManagerMachines(addressBook));


    }

    @Override
    public UserPrefs getUserPrefs() {
        return userPrefs;
    }

}
