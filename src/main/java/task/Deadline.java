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
        return this.deadline;
    }


    /**
     * Returns a title and possibly any local timing data of a task.
     * 
     * @return String
     */
    @Override
    public String getDescription() {
        return this.title + " (by: " + this.deadline + ")";
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
