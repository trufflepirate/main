package seedu.address.logic.commands.machine;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_JOBS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MACHINES;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;

/**
 * Removes a machine from MakerManager address book
 */
public class ManageMachineCommand extends Command {

    public static final String COMMAND_WORD = "manageMachine";
    public static final String OPTION_REMOVE = "remove";
    public static final String OPTION_FLUSH = "flush";
    public static final String OPTION_CLEAN = "clean";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": flush/clean/remove a machine from Maker Manager address book" + "\nExample: " + COMMAND_WORD
            + " " + "JJPrinter flush";

    public static final String MESSAGE_REMOVE_MACHINE_SUCCESS = "Machine removed: %1$s";
    public static final String MESSAGE_FLUSH_MACHINE_SUCCESS = "Machine has been flushed";
    public static final String MESSAGE_CLEAN_MACHINE_SUCCESS = "Machine has been cleaned";
    public static final String MESSAGE_MACHINE_NOT_FOUND = "Machine not found";
    public static final String MESSAGE_MACHINE_STILL_HAVE_JOBS = "Machine still have jobs";
    public static final String MESSAGE_MACHINE_STILL_HAVE_UNFINISHED_JOBS = "Machine still have unfinished jobs";
    public static final String MESSAGE_MACHINE_DOES_NOT_HAVE_CLEANABLE_JOBS = "Machine is already clean";
    public static final String MESSAGE_NO_SUCH_MANAGE_MACHINE_COMMAND = "No such option to manage machine";
    public static final String MESSAGE_MACHINE_NOT_FLUSHED = "Machine is not flushed";
    private static final String MESSAGE_ACCESS_DENIED =
        "Non admin user is not allowed to manage a machine from maker manager";

    private MachineName machineName;
    private String option;

    /**
     * @param machineName is the name of the machine to be removed
     */

    public ManageMachineCommand(MachineName machineName, String option) {
        requireAllNonNull(machineName, option);
        this.machineName = machineName;
        this.option = option;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isLoggedIn()) {
            throw new CommandException(MESSAGE_ACCESS_DENIED);
        }

        Machine machineToManage = model.findMachine(machineName);
        if (machineToManage != null) {
            switch (option) {
            case OPTION_REMOVE:
                if (machineToManage.hasJobs()) {
                    throw new CommandException(MESSAGE_MACHINE_STILL_HAVE_JOBS);
                }
                model.removeMachine(machineToManage);
                model.commitAddressBook();
                model.updateFilteredMachineList(PREDICATE_SHOW_ALL_MACHINES);
                return new CommandResult(String.format(MESSAGE_REMOVE_MACHINE_SUCCESS, machineToManage));
            case OPTION_FLUSH:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Require confirmation");
                alert.setHeaderText("Are you sure you want to flush " + machineToManage.getName().fullName + "?");
                alert.setContentText("Are you ok with this?" + "\nAlternatively, you can click cancel and proceed to move "
                    + "all the jobs in the machine to another machine"
                    + "\nOtherwise, all jobs in the machine will be removed");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    model.flushMachine(machineToManage);
                    model.commitAddressBook();
                    model.updateFilteredMachineList(PREDICATE_SHOW_ALL_MACHINES);
                    model.updateFilteredJobListInAllMachines(PREDICATE_SHOW_ALL_JOBS);
                    return new CommandResult(String.format(MESSAGE_FLUSH_MACHINE_SUCCESS, machineToManage));
                } else {
                    return new CommandResult(String.format(MESSAGE_MACHINE_NOT_FLUSHED, machineToManage));
                }

            case OPTION_CLEAN:
                if (machineToManage.hasCleanableJobs()) {
                    model.cleanMachine(machineToManage);
                    model.commitAddressBook();
                    model.updateFilteredMachineList(PREDICATE_SHOW_ALL_MACHINES);
                    model.updateFilteredJobListInAllMachines(PREDICATE_SHOW_ALL_JOBS);
                    return new CommandResult(String.format(MESSAGE_CLEAN_MACHINE_SUCCESS, machineToManage));
                } else {
                    return new CommandResult(
                        String.format(MESSAGE_MACHINE_DOES_NOT_HAVE_CLEANABLE_JOBS, machineToManage));
                }
            default:
                throw new CommandException(MESSAGE_NO_SUCH_MANAGE_MACHINE_COMMAND);
            }
        } else {
            throw new CommandException(MESSAGE_MACHINE_NOT_FOUND);
        }


    }
}
