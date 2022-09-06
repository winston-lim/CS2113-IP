public class Todo extends Task {
    private static final String TODO_PREFIX = "[T] ";

    Todo(String description, int id) {
        super(description, id);
    }

    @Override
    public final String getStatusDescription() {
        return TODO_PREFIX + this.getStatusIcon() + " " + this.getDescription();
    }
}
