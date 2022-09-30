package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import parser.Parser;

public class Event extends Task {
    // Constants
    private static final String EVENT_PREFIX = "[E] ";
    private static final String TASK_TYPE = "E";

    // Properties
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime,
            boolean isDone) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean equalDate(LocalDate date) {
        return this.startDateTime.toLocalDate().equals(date)
                || this.endDateTime.toLocalDate().equals(date);
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
        return this.startDateTime.toString() + " " + this.endDateTime.toString();
    }

    /**
     * Returns a title and possibly any local timing data of a task.
     * 
     * @return String
     */
    @Override
    public String getDescription() {
        return this.title + " (at: " + this.startDateTime.format(Parser.DATETIME_DECODE_FORMATTER)
                + " - " + this.endDateTime.format(Parser.DATETIME_DECODE_FORMATTER) + ")";
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
