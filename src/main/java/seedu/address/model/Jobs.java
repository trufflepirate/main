package seedu.address.model;

/**
 * Represents a Printing job in printQ9000
 */
public class Jobs {
    private String name;
    private String machine;
    //TimeObj time = new Time();

    public Jobs(String name, String machine) {
        this.name = name;
        this.machine = machine;
    }

    public String getName() {
        return name;
    }

    public String getMachine() {
        return machine;
    }

    /*public long getTime() {
        return time.getTime;
    }*/

    public void changeName(String name) {
        this.name = name;
    }

    public void changeMachine(String machine) {
        this.machine = machine;
    }

    /*public void changeTime(long time) {
        this.time.setTime(time);
    }*/
}
