package task;

public class Event extends Task {
    private static final String EVENT_PREFIX = "[E] ";
    private static final String TASK_TYPE = "E";

    private final String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, String duration, boolean isDone) {
        super(description, isDone);
        this.duration = duration;
    }

    /**
     * Returns the type of the task, which is usually a single character surrounded by square
     * brackets.
     * 
     * @return String
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Returns any local time related data, for this case it is the duration of event.
     * 
     * @return String
     */
    @Override
    public String getTaskTiming() {
        return this.duration;
    }

    /**
     * Returns a title and possibly any local timing data of a task.
     * 
     * @return String
     */
    @Override
    public String getDescription() {
        return this.title + " (at: " + this.duration + ")";
    }

    /**
     * Returns all known information about a task.
     * 
     * @return String
     */
    @Override
    public final String getStatusDescription() {
        return EVENT_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
