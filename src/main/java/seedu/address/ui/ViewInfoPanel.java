package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.MachinePanelSelectiononChangedEvent;
import seedu.address.model.job.Job;
import seedu.address.model.machine.Machine;
import seedu.address.ui.job.JobCard;


/**
 * Displays the info of an item being selected
 */
public class ViewInfoPanel extends UiPart<Region> {

    private static final String FXML = "ViewInfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ViewInfoPanel.class);

    @FXML
    private ListView<Job> jobsListView;

    public ViewInfoPanel() {
        super(FXML);
        setConnections();
        registerAsAnEventHandler(this);

    }

    private void setConnections() {
        jobsListView.setCellFactory(listView -> new JobListViewCell());

    }

    @Subscribe
    private void handleSelectedCardEvent(MachinePanelSelectiononChangedEvent event) {

        logger.info("Handling card selected event");
        Machine machineSelected = event.getNewSelection();
        List<Job> machineJobs = machineSelected.getJobs();
        ObservableList<Job> machineJobsObservable = FXCollections.observableArrayList();
        machineJobsObservable.addAll(machineJobs);
        jobsListView.setItems(machineJobsObservable);
    }
    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Job}
     */

    class JobListViewCell extends ListCell<Job> {
        @Override
        protected void updateItem(Job job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JobCard(job, getIndex() + 1).getRoot());
            }
        }
    }
}
