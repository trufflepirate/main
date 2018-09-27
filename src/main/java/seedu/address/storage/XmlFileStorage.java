package seedu.address.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.XmlUtil;
import seedu.address.storage.machine.XmlSerializableMakerManagerAddressBook;

/**
 * Stores addressbook data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given addressbook data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableAddressBook addressBook)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, addressBook);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns address book in the file or an empty address book
     */
    public static XmlSerializableAddressBook loadDataFromSaveFile(Path file) throws DataConversionException,
                                                                            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableAddressBook.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

    //MakerManager address book xml file functions below

    /**
     * Saves the given maker manager address book data to the specified file
     */
    public static void saveMakerManagerDataToFile(Path file, XmlSerializableMakerManagerAddressBook addressBook)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, addressBook);
        } catch (JAXBException e) {
            throw  new AssertionError("Unexpected exception" + e.getMessage(), e);
        }
    }
    /**
     * Returns makerManager address book in the file or an empty address book
     */

    public static XmlSerializableMakerManagerAddressBook loadMakerManagerDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableMakerManagerAddressBook.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

}
