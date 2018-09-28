package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.machine.Machine;

/**
 * Represents a selection change in the Machine List Panel
 */
public class MachinePanelSelectiononChangedEvent extends BaseEvent {

    private final Machine newSelection;

    public MachinePanelSelectiononChangedEvent(Machine newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Machine getNewSelection() { return newSelection; }
}
