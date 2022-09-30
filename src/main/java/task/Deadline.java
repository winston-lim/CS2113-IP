package task;

import java.time.LocalDate;
import parser.Parser;

public class Deadline extends Task {
    private static final String DEADLINE_PREFIX = "[D] ";
    private static final String TASK_TYPE = "D";

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

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String getTaskTiming() {
        return this.deadline.toString();
    }

    @Override
    public String getDescription() {
        return this.title + " (by: " + this.deadline.format(Parser.DATE_DECODE_FORMATTER) + ")";
    }

    @Override
    public final String getStatusDescription() {
        return DEADLINE_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
