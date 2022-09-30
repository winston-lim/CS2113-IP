package task;

public class Todo extends Task {
    private static final String TODO_PREFIX = "[T] ";
    private static final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
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
     * Returns all known information about a task.
     * 
     * @return String
     */
    @Override
    public final String getStatusDescription() {
        return TODO_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
