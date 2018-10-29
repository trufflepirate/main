package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import seedu.address.model.AddressBook;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineStatus;

/**
 * A utility class containing a list of {@code Machine} objects to be used in tests.
 */

public class ValidMachines {

    public static final Machine JJPrinter = new MachineBuilder()
            .withMachineName("JJPrinter")
            .withMachineStatus(MachineStatus.ENABLED)
            .build();

    public static final Machine TYPrinter = new MachineBuilder()
            .withMachineName("TYPrinter")
            .withMachineStatus(MachineStatus.DISABLED)
            .build();

    public static final Machine JiaHuaPrinter = new MachineBuilder()
            .withMachineName("JiaHuaPrinter")
            .withMachineStatus(MachineStatus.ENABLED)
            .build();


    public static AddressBook getMachinesData() {
        AddressBook makerManagerMachinesData = new AddressBook();
        for (Machine m : getValidMachines()) {
            makerManagerMachinesData.addMachine(m);
        }
        return makerManagerMachinesData;
    }
    public static List<Machine> getValidMachines () {
        return new ArrayList<>(Arrays.asList(JJPrinter, TYPrinter));
    }
}
