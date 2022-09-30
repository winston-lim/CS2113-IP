package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import parser.Parser;

public class Event extends Task {
    private static final String EVENT_PREFIX = "[E] ";
    private static final String TASK_TYPE = "E";

    private final LocalDateTime duration;

    public Event(String description, LocalDateTime duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, LocalDateTime duration, boolean isDone) {
        super(description, isDone);
        this.duration = duration;
    }

    public boolean equalDate(LocalDate date) {
        return this.duration.toLocalDate().equals(date);
    }

    @Override
    public String getTaskType() {
        System.out.println(getTaskTiming());
        return TASK_TYPE;
    }

    @Override
    public String getTaskTiming() {
        return this.duration.toString();
    }

    @Override
    public String getDescription() {
        return this.title + " (at: " + this.duration.format(Parser.DATETIME_DECODE_FORMATTER) + ")";
    }

    @Override
    public final String getStatusDescription() {
        return EVENT_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
