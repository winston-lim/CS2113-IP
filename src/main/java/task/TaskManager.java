package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exception.ExceptionManager;
import exception.InvalidFileDataException;
import exception.TaskNotFoundException;
import file.FileManager;
import parser.Parser;
import user.UserInteraction;

public class TaskManager implements TaskManagerInterface {
    private static final String FILE_PATH = "./data.txt";

    protected static final String LIST_TASK_START_TEXT = "Here are the tasks in your list:";
    protected static final String ADD_TASK_START_TEXT = "Got it! Added this task: ";
    protected static final String DELETE_TASK_START_TEXT = "Noted. I've removed this task:";
    protected static final String MARK_TASK_START_TEXT = "I've marked this task: ";
    protected static final String MARK_TASK_ERROR_TEXT = "Task is already marked";
    protected static final String UNMARK_TASK_START_TEXT = "I've unmarked this task: ";
    protected static final String UNMARK_TASK_ERROR_TEXT = "Task has not been marked";
    protected static final String COMMAND_END_TEXT = "Remaining number of tasks: ";

    protected static final String DEFAULT_INDENTATION = "    ";
    protected static final String DEFAULT_DELIMITER = " ";
    protected static final String ORDERED_LIST_PREFIX = ". ";

    private static final int DIFF_INDEX_FROM_ID = 1;
    private static final int MINIMUM_TASK_NUMBER = 1;

    private final List<Task> recordedTasks;
    private final FileManager fileManager;

    /**
     * Default Constructor method. Throws FileNotFoundException, InvalidFileDataException, but will
     * be handled immediately.
     */
    public TaskManager() {
        this.fileManager = new FileManager(FILE_PATH);
        this.recordedTasks = new ArrayList<Task>();
        File data = this.fileManager.readFromFile();
        if (data.exists()) {
            try {
                Scanner sc = new Scanner(data);
                while (sc.hasNextLine()) {
                    try {
                        recordedTasks.add(Parser.parseDataToTask(sc.nextLine()));
                    } catch (InvalidFileDataException e) {
                        ExceptionManager.handleException(e);
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) { // FileNotFound exception is caught here
                ExceptionManager.handleException(e);
            }
        }
    }

    public List<Task> getRecordedTasks() {
        return this.recordedTasks;
    }

    /**
     * Lists all recorded tasks.
     */
    @Override
    public final void listTasks() {
        List<String> messages = new ArrayList<String>();
        messages.add(LIST_TASK_START_TEXT);

        for (int i = 1; i <= recordedTasks.size(); ++i) {
            messages.add(i + ORDERED_LIST_PREFIX + recordedTasks.get(i - 1).getStatusDescription());
        }

        messages.add(COMMAND_END_TEXT + recordedTasks.size());
        UserInteraction.printNormalResponse(messages.toArray(new String[0]));
    }

    /**
     * Creates a new task.
     * 
     * @param task a Task to be added
     */
    public final void addTask(Task task) throws IOException {
        this.recordedTasks.add(task);

        UserInteraction.printNormalResponse(ADD_TASK_START_TEXT,
                DEFAULT_INDENTATION
                        + recordedTasks.get(this.recordedTasks.size() - 1).getStatusDescription(),
                COMMAND_END_TEXT + this.recordedTasks.size());

        // persist updates to local storage
        this.saveTasks(recordedTasks);
    }

    /**
     * Removes a task if it exists.
     * 
     * @param id an integer specifying task to be deleted
     */
    public void deleteTask(int id) throws TaskNotFoundException, IOException {
        if (id < MINIMUM_TASK_NUMBER || id > recordedTasks.size()) {
            throw new TaskNotFoundException();
        }
        int taskIndex = id - DIFF_INDEX_FROM_ID;
        String description = recordedTasks.get(taskIndex).getStatusDescription();
        recordedTasks.remove(taskIndex);
        UserInteraction.printNormalResponse(DELETE_TASK_START_TEXT,
                DEFAULT_INDENTATION + description, COMMAND_END_TEXT + recordedTasks.size());
        // persist updates to local storage
        this.saveTasks(recordedTasks);
    }

    /**
     * Marks a task as done by calling Task.setIsDone.
     * 
     * @param id an integer specifying task to be marked as done
     */
    @Override
    public final void markTask(int id) throws TaskNotFoundException, IOException {
        if (id < MINIMUM_TASK_NUMBER || id > recordedTasks.size()) {
            throw new TaskNotFoundException();
        }

        int taskIndex = id - DIFF_INDEX_FROM_ID;

        if (recordedTasks.get(taskIndex).getStatus()) {
            UserInteraction.printNormalResponse(MARK_TASK_ERROR_TEXT);
            return;
        }

        recordedTasks.get(taskIndex).setStatus(true);
        UserInteraction.printNormalResponse(MARK_TASK_START_TEXT,
                recordedTasks.get(taskIndex).getStatusDescription());
        // persist updates to local storage
        this.saveTasks(recordedTasks);
    }

    /**
     * Marks a task as not done by calling Task.setIsDone.
     * 
     * @param id an integer specifying task to be marked as not done
     */
    @Override
    public final void unmarkTask(int id) throws TaskNotFoundException, IOException {
        if (id < MINIMUM_TASK_NUMBER || id > recordedTasks.size()) {
            throw new TaskNotFoundException();
        }

        int taskIndex = id - DIFF_INDEX_FROM_ID;

        if (!recordedTasks.get(taskIndex).getStatus()) {
            UserInteraction.printNormalResponse(UNMARK_TASK_ERROR_TEXT);
            return;
        }

        recordedTasks.get(taskIndex).setStatus(false);
        UserInteraction.printNormalResponse(UNMARK_TASK_START_TEXT,
                recordedTasks.get(taskIndex).getStatusDescription());
        // persist updates to local storage
        saveTasks(recordedTasks);
    }

    private void saveTasks(List<Task> tasks) throws IOException {
        String fileContent = Parser.stringifyTasks(this.recordedTasks);
        this.fileManager.writeToFile(fileContent);
    }
}
