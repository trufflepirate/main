package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private UserPrefs userPrefs;
    private GuiSettings tempGuiSettings;
    private Path addressBookFilePath;
    private Path makerManagerMachinesFilePath;
    private Path makerManagerAdminsFilePath;
    private Path makerManagerJobsFilePath;
    @Before
    public void setup() {
        userPrefs = new UserPrefs();
        addressBookFilePath = Paths.get("data\\addressbook.xml");
        makerManagerMachinesFilePath = Paths.get("data\\makerManagerMachines.xml");
        makerManagerAdminsFilePath = Paths.get("data\\makerManagerAdmins.xml");
        makerManagerJobsFilePath = Paths.get("data\\makerManagerJobs.xml");
    }

    @Test
    public void getGuiSettings() {
        assertNotNull(userPrefs.getGuiSettings());
    }
    @Test
    public void setNewGuiSettings() {
        userPrefs.updateLastUsedGuiSetting(createNewGuiSettings());
        GuiSettings guiSettings = createNewGuiSettings();
        assertEquals(userPrefs.getGuiSettings(), guiSettings);
    }
    @Test
    public void getAddressBookFilePath() {
        assertEquals(userPrefs.getAddressBookFilePath(), addressBookFilePath);
    }
    @Test
    public void getMakerManagerMachinesFilePath() {
        assertEquals(userPrefs.getMakerManagerMachinesFilePath(), makerManagerMachinesFilePath);
    }
    @Test
    public void getMakerManagerAdminsFilePath() {
        assertEquals(userPrefs.getMakerManagerAdminsFilePath(), makerManagerAdminsFilePath);
    }
    @Test
    public void getMakerManagerJobsFilePath() {
        assertEquals(userPrefs.getMakerManagerJobsFilePath(), makerManagerJobsFilePath);
    }
    //-----------------------Helper methods-------------------------//
    private GuiSettings createNewGuiSettings() {
        return new GuiSettings(200.0, 200.0, 0, 0);
    }
}
