public class Task {
    private final String description;
    private final int id;
    private boolean isDone;

    /**
     * Constructor method.
     * 
     * @param description a string of the description of the task
     * @param id a integer representing a unique identifier of the task
     */
    public Task(String description, int id) {
        this.description = description;
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
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getStatusDescription() {
        return this.id + ". " + "[" + getStatusIcon() + "] " + getDescription();
    }
}
