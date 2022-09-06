public class Deadline extends Task {
    private static final String DEADLINE_PREFIX = "[D] ";
    private final String deadline;

    Deadline(String description, int id, String deadline) {
        super(description, id);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
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
