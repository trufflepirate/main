package seedu.address.ui.machine;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import seedu.address.model.machine.Machine;
import seedu.address.ui.UiPart;



/**
 * A UI component that displays information of a {@code Machine}
 */
public class MachineCard extends UiPart<Region> {

    private static final String FXML = "MachineListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Machine machine;

    @FXML
    private HBox cardPane;
    @FXML
    private Label machineName;
    @FXML
    private Label id;
    @FXML
    private FlowPane machineStatus;

    public MachineCard(Machine machine, int displayIndex) {
        super(FXML);
        this.machine = machine;
        id.setText(displayIndex + ". ");
        machineName.setText(machine.getName().fullName);
        Label machineStatusLabel = new Label(machine.getStringStatus());
        machineStatusLabel.setStyle("-fx-font: 12 arial;"
                + "-fx-text-fill: black;"
                + "-fx-padding: 2;"
                + "-fx-text-alignment: center");
        machineStatusLabel.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#0ec10e"),
                        new CornerRadii(2),
                        new javafx.geometry.Insets(0))));

        Label machineAvailabilityLabel = new Label(machine.getStringStatus().equals("Enabled") ? "Available" : "Unavailable");
        machineAvailabilityLabel.setStyle("-fx-font: 12 arial;"
                + "-fx-text-fill: black;"
                + "-fx-padding: 2;"
                + "-fx-text-alignment: center");

        machineAvailabilityLabel.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("#0ec10e"),
                        new CornerRadii(2),
                        new javafx.geometry.Insets(0))));

        machineStatus.getChildren().add(machineStatusLabel);
        machineStatus.getChildren().add(machineAvailabilityLabel);
        machineStatus.setHgap(4);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MachineCard)) {
            return false;
        }

        // state check
        MachineCard machineCard = (MachineCard) other;
        return id.getText().equals(machineCard.id.getText())
                && machine.equals(machineCard.machine);
    }
}
