package seedu.address.ui.job;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.job.Job;
import seedu.address.ui.UiPart;
import seedu.address.ui.machine.MachineCard;

/**
 * An UI component that displays information of a {@code Job}.
 */
public class JobCard extends UiPart<Region> {

    private static final String FXML = "JobListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Job job;

    @FXML
    private HBox cardPane;
    @FXML
    private Label jobName;
    @FXML
    private FlowPane details;
    @FXML
    private FlowPane tags;
    @FXML
    private Label jobDescription;

    public JobCard(Job job, int displayIndex) {
        super(FXML);
        this.job = job;
        jobName.setText(job.getJobName().fullName);

        Label machineNameLabel = new Label(job.getMachine().getName().fullName);
        //TODO Tianyuan change the way timestamp is given
        //Label timeStampLabel = new Label(job.getTime().toString());
        Label ownerNameLabel = new Label(job.getOwner().getName().fullName);
        Label priorityLabel = new Label(job.getPriority().toString());

        details.getChildren().add(machineNameLabel);
        details.getChildren().add(ownerNameLabel);
        details.setHgap(4);

        tags.getChildren().add(priorityLabel);

        jobDescription.setText(job.getJobNote().toString());



        // TODO: 10-Oct-18 time to be displayed
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
        JobCard jobCard = (JobCard) other;
        return job.equals(jobCard.job);
    }
}
