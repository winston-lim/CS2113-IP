package task;

public class Task implements TaskInterface {
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

    @Override
    public final boolean getStatus() {
        return this.isDone;
    }

    @Override
    public final void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public final String getStatusIcon() {
        return (this.isDone ? STATUS_DONE : STATUS_NOT_DONE); // mark done task with X
    }

    @Override
    public String getDescription() {
        return this.title;
    }

    @Override
    public String getStatusDescription() {
        return getStatusIcon() + " " + getDescription();
    }
}
