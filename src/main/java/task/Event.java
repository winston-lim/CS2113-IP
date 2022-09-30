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

    /**
     * Returns the type of the task, which is usually a single character surrounded by square
     * brackets.
     * 
     * @return String
     */
    @Override
    public String getTaskType() {
        System.out.println(getTaskTiming());
        return TASK_TYPE;
    }

    /**
     * Returns any local time related data, for this case it is the duration of event.
     * 
     * @return String
     */
    @Override
    public String getTaskTiming() {
        return this.duration.toString();
    }

    /**
     * Returns a title and possibly any local timing data of a task.
     * 
     * @return String
     */
    @Override
    public String getDescription() {
        return this.title + " (at: " + this.duration.format(Parser.DATETIME_DECODE_FORMATTER) + ")";
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
