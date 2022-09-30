package task;

import java.time.LocalDate;

public class Todo extends Task {
    // Constants
    private static final String TODO_PREFIX = "[T] ";
    private static final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Checks if the date of task is equal to a given date.
     * 
     * @param date the date to check with
     * @return boolean
     */
    public boolean equalDate(LocalDate date) {
        return true;
    }


    /**
     * Returns a string representation of a task's timing.
     * 
     * @return String
     */
    public String getTaskTiming() {
        return "";
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
