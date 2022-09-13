package task;

public class Event extends Task {
    private static final String EVENT_PREFIX = "[E] ";
    private final String duration;

    Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, String duration, boolean isDone) {
        super(description, isDone);
        this.duration = duration;
    }

    public String getDuration() {
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
