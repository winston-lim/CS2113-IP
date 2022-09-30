package task;

public class Deadline extends Task {
    private static final String DEADLINE_PREFIX = "[D] ";
    private static final String TASK_TYPE = "D";

    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String getTaskTiming() {
        return this.deadline;
    }

    @Override
    public String getDescription() {
        return this.title + " (by: " + this.deadline + ")";
    }

    @Override
    public final String getStatusDescription() {
        return DEADLINE_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
