package task;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import console.Console;
import exception.InsufficentArgumentsException;
import exception.InvalidDataException;
import exception.TaskNotFoundException;
import file.FileManager;

public class TaskManager implements TaskManagerInterface {
    private static final String DEADLINE_DIVIDER = "/by";
    private static final String DURATION_DIVIDER = "/at";

    private static final String TASK_TODO = "todo";
    private static final String TASK_DEADLINE = "deadline";
    private static final String TASK_EVENT = "event";

    private static final int TASK_NUMBER_INDEX = 0;
    private static final int MINIMUM_TASK_NUMBER = 1;

    private final List<Task> recordedTasks;

    private static FileManager fm = new FileManager();

    public TaskManager() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            tasks = fm.getTasks();
        } catch (InvalidDataException e) {
            Console.printErrorResponse("could not get saved data");
        }
        this.recordedTasks = tasks;
        for (Task t : recordedTasks) {
            System.out.println(t.getStatusDescription());
        }
    }

    /**
     * Lists all recorded tasks.
     */
    @Override
    public final void listTasks() {
        List<String> messages = new ArrayList<String>();
        messages.add("Here are the tasks in your list:");

        for (int i = 1; i <= recordedTasks.size(); ++i) {
            messages.add(i + ". " + recordedTasks.get(i - 1).getStatusDescription());
        }

        messages.add("Total number of tasks is: " + recordedTasks.size());
        Console.printNormalResponse(messages.toArray(new String[0]));
    }

    /**
     * Creates a new task.
     * 
     * @param task a string representing the task type
     * @param args an array of strings from user inputs
     */
    @Override
    public final void addTask(String task, String[] args) throws InsufficentArgumentsException {
        if (args.length == 0) {
            throw new InsufficentArgumentsException();
        }

        String title;
        int index;

        switch (task) {
        case TASK_TODO:
            title = String.join(" ", args);
            recordedTasks.add(new Todo(title));
            break;
        case TASK_DEADLINE:
            index = Arrays.asList(args).indexOf(DEADLINE_DIVIDER);
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String deadline = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Deadline(title, deadline));
            break;
        case TASK_EVENT:
            index = Arrays.asList(args).indexOf(DURATION_DIVIDER);
            title = String.join(" ", Arrays.copyOfRange(args, 0, index));
            String duration = String.join(" ", Arrays.copyOfRange(args, index + 1, args.length));
            recordedTasks.add(new Event(title, duration));
            break;
        default:
        }

        Console.printNormalResponse("Got it! Added this task: ",
                "    " + recordedTasks.get(this.recordedTasks.size() - 1).getStatusDescription(),
                "You now have: " + this.recordedTasks.size() + " tasks");
        try {
            fm.saveTasks(this.recordedTasks);
        } catch (Exception e) {
            System.out.println("cannot save task");
        }
    }

    /**
     * Marks a task as done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    @Override
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
            Console.printNormalResponse("Task is already marked");
            return;
        }

        recordedTasks.get(taskNum - 1).setStatus(true);
        Console.printNormalResponse("I've marked this task: ",
                recordedTasks.get(taskNum - 1).getStatusDescription());
    }

    /**
     * Marks a task as not done by calling Task.setIsDone.
     * 
     * @param args a list of string arguments provided by user
     */
    @Override
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
            Console.printNormalResponse("Task has not been marked");
            return;
        }

        recordedTasks.get(taskNum - 1).setStatus(false);
        Console.printNormalResponse("I've unmarked this task: ",
                recordedTasks.get(taskNum - 1).getStatusDescription());
    }
}
