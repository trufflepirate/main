package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.Date;
import java.util.logging.Logger;

import org.controlsfx.control.StatusBar;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.ui.AdminLoginEvent;
import seedu.address.commons.events.ui.AdminLogoutEvent;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    public static final String SYNC_STATUS_INITIAL = "Not updated yet in this session";
    public static final String SYNC_STATUS_UPDATED = "Last Updated: %s";
    public static final String SET_ADMIN_LOGIN = "[ADMIN_MODE]";
    public static final String CLEAR_ADMIN_LOGIN = " ";

    /**
     * Used to generate time stamps.
     *
     * TODO: change clock to an instance variable.
     * We leave it as a static variable because manual dependency injection
     * will require passing down the clock reference all the way from MainApp,
     * but it should be easier once we have factories/DI frameworks.
     */
    private static Clock clock = Clock.systemDefaultZone();

    private static final Logger logger = LogsCenter.getLogger(StatusBarFooter.class);

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private StatusBar syncStatus;
    @FXML
    private StatusBar saveLocationStatus;
    @FXML
    private StatusBar adminLoginStatus;


    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        setSyncStatus(SYNC_STATUS_INITIAL);
        setSaveLocation(Paths.get(".").resolve(saveLocation).toString());
        changeAdminLoginStatus(CLEAR_ADMIN_LOGIN);
        registerAsAnEventHandler(this);
    }

    /**
     * Sets the clock used to determine the current time.
     */
    public static void setClock(Clock clock) {
        StatusBarFooter.clock = clock;
    }

    /**
     * Returns the clock currently in use.
     */
    public static Clock getClock() {
        return clock;
    }

    private void setSaveLocation(String location) {
        Platform.runLater(() -> saveLocationStatus.setText(location));
    }

    private void setSyncStatus(String status) {
        Platform.runLater(() -> syncStatus.setText(status));
    }

    private void changeAdminLoginStatus(String status) {
        Platform.runLater(() -> adminLoginStatus.setText(status));
    }

    //TODO: Are these bypassing UIManager?
    @Subscribe
    public void handleAddressBookChangedEvent(AddressBookChangedEvent abce) {
        long now = clock.millis();
        String lastUpdated = new Date(now).toString();
        logger.info(LogsCenter.getEventHandlingLogMessage(abce, "Setting last updated status to " + lastUpdated));
        setSyncStatus(String.format(SYNC_STATUS_UPDATED, lastUpdated));
    }

    @Subscribe
    public void handleAdminLoginEvent(AdminLoginEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Setting Login"));
        changeAdminLoginStatus(SET_ADMIN_LOGIN);
    }

    @Subscribe
    public void handleAdminLogoutEvent(AdminLogoutEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Clearing Login"));
        changeAdminLoginStatus(CLEAR_ADMIN_LOGIN);
    }

}
