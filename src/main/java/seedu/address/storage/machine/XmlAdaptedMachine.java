package seedu.address.storage.machine;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.storage.XmlAdaptedTag;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;
import java.util.stream.Collectors;

public class XmlAdaptedMachine {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Machine's %s field is missing!";

    @XmlElement(required = true)
    private String machineName;
    @XmlElement(required = true)
    private Boolean status;

    @XmlElement
    private List<XmlAdaptedJobName> jobs = new ArrayList<>();
    @XmlElement
    private Set<XmlAdaptedTag> tags = new HashSet<>();

    /**
     * Constructs an XmlAdaptedMachine.
     * This is the no-arg constructor that is required by JAXB
     */
    public XmlAdaptedMachine() {}

    /**
     * Constructs an {@code XmlAdaptedMachine} with the given machine details.
     */
    public XmlAdaptedMachine(String machineName, Boolean status, List<XmlAdaptedJobName> jobs, Set<XmlAdaptedTag> tags) {
        this.machineName = machineName;
        this.status = status;
        this.jobs = jobs;
        this.tags = tags;
    }

    /**
     * Concerts a given Machine into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedMachine(??)
     */
    public XmlAdaptedMachine(Machine source) {
        machineName = source.getName().fullName;
        status = source.getStatus();
        jobs = source.getJobs().stream()
                .map(XmlAdaptedJobName::new)
                .collect(Collectors.toList());
        tags = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toSet());
    }


    public Machine toModelType() throws IllegalValueException {
        if (machineName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,Machine.class.getSimpleName()));
        }

        if (!Machine.isValidName(machineName)) {
            throw new IllegalValueException(Machine.MESSAGE_NAME_CONSTRAINTS);
        }

        final MachineName name = new MachineName(machineName);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,"STATUS"));
        }

        final List<Name> finalJobs = new ArrayList<>();
        for (XmlAdaptedJobName job : jobs) {
            finalJobs.add(job.toModelType());
        }

        final List<Tag> modelTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tags) {
            modelTags.add(tag.toModelType());
        }

        final Set<Tag> finalTags = new HashSet<>(modelTags);
        return new Machine(name,finalJobs,finalTags,status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedMachine)) {
            return false;
        }

        XmlAdaptedMachine otherMachine = (XmlAdaptedMachine) other;
        return Objects.equals(machineName,otherMachine.machineName)
                && Objects.equals(status,otherMachine.status)
                && jobs.equals(otherMachine.jobs)
                && tags.equals(otherMachine.tags);
    }
}
