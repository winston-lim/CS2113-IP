import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskManager {
    private static final String DEADLINE_DIVIDER = "/by";
    private static final String DURATION_DIVIDER = "/at";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
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
    public final void addTask(String command, String[] args)
            throws InsufficentArgumentsException, CommandNotFoundException {
        if (args.length == 0) {
            throw new InsufficentArgumentsException();
        }
        String title;
        int index;
        switch (command) {
        case COMMAND_TODO:
            taskCount++;
            title = String.join(" ", args);
            recordedTasks.add(new Todo(title, taskCount));
            break;
        case COMMAND_DEADLINE:
            taskCount++;
            index = Arrays.asList(args).indexOf(DEADLINE_DIVIDER);
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String deadline = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Deadline(title, taskCount, deadline));
            break;
        case COMMAND_EVENT:
            taskCount++;
            index = Arrays.asList(args).indexOf(DURATION_DIVIDER);
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String duration = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Event(title, taskCount, duration));
            break;
        default:
            throw new CommandNotFoundException();
        }
        ConversationManager.printNormalResponse("Got it! Added this task: ",
                "    " + recordedTasks.get(taskCount - 1).getStatusDescription(),
                "You now have: " + taskCount + " tasks");
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
        ConversationManager.printNormalResponse(messages.toArray(new String[0]));
    }

    /**
     * Marks a task as done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    public final void markTask(String[] args)
            throws InsufficentArgumentsException, TaskNotFoundException {
        if (args.length == 0) {
            throw new InsufficentArgumentsException();
        }

        int taskNum = Integer.parseInt(args[TASK_NUMBER_INDEX]);

        if (taskNum < MINIMUM_TASK_NUMBER || taskNum > recordedTasks.size()) {
            throw new TaskNotFoundException();
        }

        if (recordedTasks.get(taskNum - 1).getStatus()) {
            ConversationManager.printNormalResponse("Task is already marked");
            return;
        }

        recordedTasks.get(taskNum - 1).setStatus(true);
        ConversationManager.printNormalResponse("I've marked this task: ",
                recordedTasks.get(taskNum - 1).getStatusDescription());
    }

    /**
     * Marks a task as not done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    public final void unmarkTask(String[] args)
            throws InsufficentArgumentsException, TaskNotFoundException {
        if (args.length == 0) {
            throw new InsufficentArgumentsException();
        }
        int taskNum = Integer.parseInt(args[TASK_NUMBER_INDEX]);

        if (taskNum < MINIMUM_TASK_NUMBER || taskNum > recordedTasks.size()) {
            throw new TaskNotFoundException();
        }

        if (!recordedTasks.get(taskNum - 1).getStatus()) {
            ConversationManager.printNormalResponse("Task has not been marked");
            return;
        }

        recordedTasks.get(taskNum - 1).setStatus(false);
        ConversationManager.printNormalResponse("I've unmarked this task: ",
                recordedTasks.get(taskNum - 1).getStatusDescription());
    }
}
