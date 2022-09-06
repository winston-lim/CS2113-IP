import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskManager {
    private static final String DEADLINE_DIVIDER = "/by";
    private static final String DURATION_DIVIDER = "/at";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final int TASK_NUMBER_INDEX = 0;
    private static final int MINIMUM_TASK_NUMBER = 1;
    private final List<Task> recordedTasks;
    private static int taskCount = 0;

    TaskManager() {
        this.recordedTasks = new ArrayList<Task>();
    }

    /**
     * Creates a new task.
     * 
     * @param command a string from user input
     * @param args an array of strings from user inputs
     */
    public final void addTask(String command, String[] args) {
        if (args.length == 0) {
            ConversationManager
                    .printErrorResponse(List.of("no such command or insufficient arguments"));
            return;
        }
        String title;
        int index;
        switch (command) {
        case TODO:
            taskCount++;
            title = String.join(" ", args);
            recordedTasks.add(new Todo(title, taskCount));
            break;
        case DEADLINE:
            taskCount++;
            index = Arrays.asList(args).indexOf(DEADLINE_DIVIDER);
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String deadline = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Deadline(title, taskCount, deadline));
            break;
        case EVENT:
            taskCount++;
            index = Arrays.asList(args).indexOf(DURATION_DIVIDER);
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String duration = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Event(title, taskCount, duration));
            break;
        default:
            ConversationManager.printErrorResponse(List.of("no such command"));
            return;
        }
        List<String> messages = new ArrayList<String>();
        messages.add("Got it! Added this task: ");
        messages.add("    " + recordedTasks.get(taskCount - 1).getStatusDescription());
        messages.add("You now have: " + taskCount + " tasks");
        ConversationManager.printNormalResponse(messages);
    }

    /**
     * Lists all recorded tasks.
     */
    public final void listTasks() {
        List<String> messages = new ArrayList<String>();
        messages.add("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; ++i) {
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
        int taskNum = Integer.parseInt(args[TASK_NUMBER_INDEX]);
        if (taskNum < MINIMUM_TASK_NUMBER || taskNum > recordedTasks.size()) { // task does not
                                                                               // exist
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
        int taskNum = Integer.parseInt(args[TASK_NUMBER_INDEX]);
        if (taskNum < MINIMUM_TASK_NUMBER || taskNum > recordedTasks.size()) { // task does not
                                                                               // exist
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
