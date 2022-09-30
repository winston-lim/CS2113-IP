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

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String getTaskTiming() {
        return this.duration;
    }

    @Override
    public String getDescription() {
        return this.title + " (at: " + this.duration + ")";
    }

    @Override
    public final String getStatusDescription() {
        return EVENT_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
