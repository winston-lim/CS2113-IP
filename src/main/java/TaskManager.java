import java.util.ArrayList;
import java.util.Arrays;
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
    public final void addTask(String command, String[] args) {
        if (args.length == 0) {
            ConversationManager
                    .printErrorResponse(List.of("no such command or insufficient arguments"));
            return;
        }
        String title;
        switch (command) {
        case "todo": {
            taskCount++;
            title = String.join(" ", args);
            recordedTasks.add(new Todo(title, taskCount));
            break;
        }
        case "deadline": {
            taskCount++;
            int index = Arrays.asList(args).indexOf("/by");
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String deadline = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Deadline(title, taskCount, deadline));
            break;
        }
        case "event": {
            taskCount++;
            int index = Arrays.asList(args).indexOf("/at");
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String duration = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Event(title, taskCount, duration));
            break;
        }
        default: {
            ConversationManager.printErrorResponse(List.of("no such command"));
            return;
        }
        }
        List<String> messages = new ArrayList<String>();
        messages.add("Got it! Added this task: ");
        messages.add("    " + recordedTasks.get(taskCount - 1).getStatusDescription());
        messages.add("You now have: " + this.taskCount + " tasks");
        ConversationManager.printNormalResponse(messages);
    }

    /**
     * Lists all recorded tasks.
     */
    public final void listTasks() {
        List<String> messages = new ArrayList<String>();
        messages.add("Here are the tasks in your list:");
        for (int i = 0; i < this.taskCount; ++i) {
            messages.add(recordedTasks.get(i).getStatusDescriptionWithId());
        }
        messages.add("Total number of tasks is: " + taskCount);
        ConversationManager.printNormalResponse(messages);
    }

    /**
     * Marks a task as done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    public final void markTask(String[] args) {
        if (args.length == 0) {
            ConversationManager.printErrorResponse(List.of("insufficient arguments"));
            return;
        }
        int taskNum = Integer.parseInt(args[0]);
        if (taskNum < 1 || taskNum > recordedTasks.size()) { // task does not exist
            ConversationManager.printErrorResponse(List.of("task does not exist"));
        } else if (recordedTasks.get(taskNum - 1).getStatus()) {
            ConversationManager.printErrorResponse(List.of("task is already marked"));
        } else { // task exists
            recordedTasks.get(taskNum - 1).setStatus(true);
            List<String> messages = new ArrayList<String>();
            messages.add("I've marked this task: ");
            messages.add(recordedTasks.get(taskNum - 1).getStatusDescription());
            ConversationManager.printNormalResponse(messages);
        }
    }

    /**
     * Marks a task as not done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    public final void unmarkTask(String[] args) {
        if (args.length == 0) {
            ConversationManager.printErrorResponse(List.of("insufficient arguments"));
            return;
        }
        int taskNum = Integer.parseInt(args[0]);
        if (taskNum < 1 || taskNum > recordedTasks.size()) { // task does not exist
            ConversationManager.printErrorResponse(List.of("task does not exist"));
        } else if (!recordedTasks.get(taskNum - 1).getStatus()) {
            ConversationManager.printErrorResponse(List.of("task is not yet marked"));
        } else { // task exists
            recordedTasks.get(taskNum - 1).setStatus(false);
            List<String> messages = new ArrayList<String>();
            messages.add("I've unmarked this task: ");
            messages.add(recordedTasks.get(taskNum - 1).getStatusDescription());
            ConversationManager.printNormalResponse(messages);
        }
    }
}
