package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exception.InvalidDataException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;


public class FileManager implements FileManagerInterface {
    private static final String FILE_RELATIVE_PATH = "./data/duke.txt";

    private static final String DATA_TOKEN_SEPARATOR = " | ";
    private static final String DATA_TOKEN_SEPARATOR_REGEX = " \\| ";
    private static final String TASK_DATE_SEPARATOR_REGEX = "\\(";

    private static final String TASK_TIMING_SEPARATOR = ":";

    private static final int TASK_TYPE_INDEX = 1;
    private static final int TASK_STATUS_INDEX = 5;
    private static final int TASK_TITLE_INDEX = 8;

    private static final char TODO_TASK_TYPE = 'T';
    private static final char DEADLINE_TASK_TYPE = 'D';
    private static final char EVENT_TASK_TYPE = 'E';
    private final File file;

    public FileManager() {
        this.file = new File(FILE_RELATIVE_PATH);
    }

    /**
     * Reads from the local data file and converts it to a usable format.w
     * 
     * @return A list of tasks from the read file, which is empty if the file does not exist or is
     *         empty.
     */
    public List<Task> getTasks() throws InvalidDataException {
        try {
            Scanner sc = new Scanner(this.file);
            List<Task> taskList = new ArrayList<Task>();
            if (this.file.exists()) {
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    String[] taskDetails = task.split(DATA_TOKEN_SEPARATOR_REGEX);
                    char taskType = taskDetails[0].charAt(0);
                    boolean taskStatus = Integer.parseInt(taskDetails[1]) == 1;
                    String taskTitle = taskDetails[2];
                    if (!(taskType - 'A' >= 0 && taskType - 'A' <= 26)) {
                        sc.close();
                        throw new Exception();
                    }
                    if (taskType == TODO_TASK_TYPE) { // no deadline or duration
                        taskList.add(new Todo(taskTitle, taskStatus));
                        continue;
                    }
                    String taskTiming = taskDetails[3];
                    if (taskType == DEADLINE_TASK_TYPE) {
                        taskList.add(new Deadline(taskTitle, taskTiming, taskStatus));
                    }
                    if (taskType == EVENT_TASK_TYPE) {
                        taskList.add(new Event(taskTitle, taskTiming, taskStatus));
                    }
                }
            }
            sc.close();
            return taskList;
        } catch (Exception e) {
            throw new InvalidDataException();
        }
    }

    private String convertTaskToText(Task task) {
        String taskDescription = task.getStatusDescription();
        char taskType = taskDescription.charAt(TASK_TYPE_INDEX);
        int taskStatus = taskDescription.charAt(TASK_STATUS_INDEX) == ' ' ? 0 : 1;
        if (taskType == TODO_TASK_TYPE) {
            return taskType + DATA_TOKEN_SEPARATOR + taskStatus + DATA_TOKEN_SEPARATOR
                    + taskDescription.substring(TASK_TITLE_INDEX);
        }
        String taskTitle =
                taskDescription.split(TASK_DATE_SEPARATOR_REGEX)[0].substring(TASK_TITLE_INDEX);
        String taskTiming =
                taskDescription.split(TASK_DATE_SEPARATOR_REGEX)[1].split(TASK_TIMING_SEPARATOR)[1];
        taskTiming = taskTiming.substring(1, taskTiming.length() - 1);
        return taskType + DATA_TOKEN_SEPARATOR + taskStatus + DATA_TOKEN_SEPARATOR + taskTitle
                + DATA_TOKEN_SEPARATOR + taskTiming;
    }

    /**
     * Saves all tasks to data file by overwriting all its contents.
     * 
     * @param tasks A list of Task
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (Task task : tasks) {
            fw.write(convertTaskToText(task) + System.lineSeparator());
        }
        fw.close();
    }
}
