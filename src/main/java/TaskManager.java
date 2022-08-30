import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> recordedTasks;
    private int taskCount = 0;

    TaskManager() {
        this.recordedTasks = new ArrayList<Task>();
    }

    /**
     * Creates a new task.
     * 
     * @param description description that the new task is created with
     */
    public final void addTask(String description) {
        taskCount++;
        recordedTasks.add(new Task(description, taskCount));
        ConversationManager.printNormalResponse(List.of("Added " + description));
    }

    /**
     * Lists all recorded tasks.
     */
    public final void listTasks() {
        if (this.recordedTasks.size() == 0) {
            ConversationManager.printNormalResponse(List.of("No recorded tasks"));
        }
        List<String> messages = new ArrayList<String>();
        for (int i = 0; i < this.taskCount; ++i) {
            messages.add(recordedTasks.get(i).getStatusDescription());
        }
        ConversationManager.printNormalResponse(messages);
    }

    /**
     * Marks a task as done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    public final void markTask(String[] args) {
        if (args.length == 0) {
            ConversationManager.printErrorResponse(List.of("task number is required"));
        }
        int taskNum = Integer.parseInt(args[0]);
        if (taskNum < 1 || taskNum > recordedTasks.size()) { // task does not exist
            ConversationManager.printErrorResponse(List.of("task does not exist"));
        } else if (recordedTasks.get(taskNum - 1).getStatus()) {
            ConversationManager.printErrorResponse(List.of("task is already marked"));
        } else { // task exists
            recordedTasks.get(taskNum - 1).setStatus(true);
            ConversationManager.printNormalResponse(
                    List.of("Marked " + recordedTasks.get(taskNum - 1).getDescription()));
        }
    }

    /**
     * Marks a task as not done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    public final void unmarkTask(String[] args) {
        if (args.length == 0) {
            ConversationManager.printErrorResponse(List.of("task number is required"));
        }
        int taskNum = Integer.parseInt(args[0]);
        if (taskNum < 1 || taskNum > recordedTasks.size()) { // task does not exist
            ConversationManager.printErrorResponse(List.of("task does not exist"));
        } else if (!recordedTasks.get(taskNum - 1).getStatus()) {
            ConversationManager.printErrorResponse(List.of("task is not yet marked"));
        } else { // task exists
            recordedTasks.get(taskNum - 1).setStatus(false);
            ConversationManager.printNormalResponse(
                    List.of("Unmarked " + recordedTasks.get(taskNum - 1).getDescription()));
        }
    }
}
