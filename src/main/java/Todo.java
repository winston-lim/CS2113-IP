public class Todo extends Task {
    Todo(String description, int id) {
        super(description, id);
    }

    @Override
    public final String getStatusDescription() {
        return "[T] " + this.getStatusIcon() + " " + this.getDescription();
    }
}
