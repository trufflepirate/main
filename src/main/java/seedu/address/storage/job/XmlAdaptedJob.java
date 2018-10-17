package seedu.address.storage.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobPriority;
import seedu.address.model.machine.Machine;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.storage.XmlAdaptedTag;
import seedu.address.storage.machine.XmlAdaptedMachine;

/**
 * JAXB-friendly version of the Job.
 */
public class XmlAdaptedJob {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Job's %s field is missing!";

    private static final Logger logger = LogsCenter.getLogger(XmlAdaptedJob.class);

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private XmlAdaptedMachine machine;
    @XmlElement(required = true)
    private String time;
    @XmlElement(required = true)
    private String owner;
    @XmlElement(required = true)
    private JobPriority priority;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    @XmlElement(required = true)
    private String note;

    private Job job;

    /**
     * Constructs an XmlAdaptedJob.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedJob() {}

    /**
     * Constructs an {@code XmlAdaptedJob} with the given job details.
     */
    public XmlAdaptedJob(String name, XmlAdaptedMachine machine, String time, String owner,
                         JobPriority priority, String note, List<XmlAdaptedTag> tagged) {
        this.name = name;
        this.machine = machine;
        this.time = time;
        this.owner = owner;
        this.priority = priority;
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
        this.note = note;
    }

    /**
     * Converts a given Job into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedJob
     */
    public XmlAdaptedJob(Job source) {
        job = source;
        name = source.getJobName().fullName;
        machine = new XmlAdaptedMachine(source.getMachine());
        time = source.getTime().toString();
        owner = source.getOwner().toString();
        priority = source.getPriority();
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
        note = source.getJobNote().toString();
    }

    /**
     * Converts this jaxb-friendly adapted job object into the model's Job object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted job
     */
    public Job toModelType() throws IllegalValueException {

        logger.info(job.getJobName().fullName);
        logger.info(job.getMachine().getName().fullName);
        return job;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.storage.job.XmlAdaptedJob)) {
            return false;
        }

        seedu.address.storage.job.XmlAdaptedJob otherJob = (seedu.address.storage.job.XmlAdaptedJob) other;
        return Objects.equals(name, otherJob.name)
                && Objects.equals(machine, otherJob.machine)
                && Objects.equals(owner, otherJob.owner)
                && Objects.equals(priority, otherJob.priority)
                && tagged.equals(otherJob.tagged);
    }
}
