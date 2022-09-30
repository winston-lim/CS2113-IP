package task;

abstract public class Task implements TaskInterface {
    private static final String STATUS_NOT_DONE = "[ ]";
    private static final String STATUS_DONE = "[X]";
    protected final String title;
    private boolean isDone;

    /**
     * Constructor method.
     * 
     * @param title a string of the title of the task
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public final boolean getStatus() {
        return this.isDone;
    }

    public final void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public final String getStatusIcon() {
        return (this.isDone ? STATUS_DONE : STATUS_NOT_DONE); // mark done task with X
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * Returns a title and possibly any local timing data of a task.
     * 
     * @return String
     */
    public String getDescription() {
        return this.getTitle();
    }

    /**
     * Returns the type of the task, which is usually a single character surrounded by square
     * brackets.
     * 
     * @return String
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Returns any local time related data, for this case it is a empty.
     * 
     * @return String
     */
    public String getTaskTiming() {
        return "";
    }

    /**
     * Returns all known information about a task.
     * 
     * @return String
     */
    public String getStatusDescription() {
        return getStatusIcon() + " " + getDescription();
    }
}
