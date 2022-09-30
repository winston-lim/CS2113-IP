package task;

import java.time.LocalDate;
import parser.Parser;

public class Deadline extends Task {
    // Constants
    private static final String DEADLINE_PREFIX = "[D] ";
    private static final String TASK_TYPE = "D";

    // Properties
    private final LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public boolean equalDate(LocalDate date) {
        return this.deadline.equals(date);
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
     * Returns any local time related data, for this case it is a deadline.
     * 
     * @return String
     */
    @Override
    public String getTaskTiming() {
        return this.deadline.toString();
    }


    /**
     * Returns a title and possibly any local timing data of a task.
     * 
     * @return String
     */
    @Override
    public String getDescription() {
        return this.title + " (by: " + this.deadline.format(Parser.DATE_DECODE_FORMATTER) + ")";
    }


    /**
     * Returns all known information about a task.
     * 
     * @return String
     */
    @Override
    public final String getStatusDescription() {
        return DEADLINE_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
