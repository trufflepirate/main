package seedu.address.storage.job;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.job.TimeStamp;


/**
 * JAXB-friendly version of the timeStamp.
 */
public class XmlAdaptedTimeStamp {
    @XmlElement(required = true)
    private String timeStamp;

    /**
     * Constructs an XmlAdaptedTimeStamp.
     * This is the no-arg constructor that is required by JAXB
     */
    public XmlAdaptedTimeStamp() {}

    /**
     * Converts a given TimeStamp into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTimeStamp
     */
    public XmlAdaptedTimeStamp(TimeStamp source) {
        this.timeStamp = source.showTime();
    }

    /**
     * Converts this jaxb-friendly adapted timeStamp object into the model's TimeStamp object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted job
     */
    public TimeStamp toModelType() throws IllegalValueException {
        return new TimeStamp(timeStamp);
    }
}
