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

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public final String getStatusDescription() {
        return TODO_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
