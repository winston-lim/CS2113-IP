public class Task {
    protected final String title;
    protected final int id;
    private boolean isDone;

    /**
     * Constructor method.
     * 
     * @param description a string of the description of the task
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
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
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
