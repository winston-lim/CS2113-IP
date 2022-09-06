public class Task {
    private static final String STATUS_NOT_DONE = "[ ]";
    private static final String STATUS_DONE = "[X]";
    protected final String title;
    protected final int id;
    private boolean isDone;

    /**
     * Constructor method.
     * 
     * @param title a string of the title of the task
     * @param id a integer representing a unique identifier of the task
     */
    public Task(String title, int id) {
        this.title = title;
        this.isDone = false;
        this.id = id;
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

    public String getDescription() {
        return this.title;
    }

    public String getStatusDescription() {
        return getStatusIcon() + " " + getDescription();
    }

    public String getStatusDescriptionWithId() {
        return this.id + ". " + this.getStatusDescription();
    }
}
