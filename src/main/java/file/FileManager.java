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
    private static final String DATA_TOKEN_SEPARATOR = " \\| ";
    private static final String TASK_DATE_SEPARATOR = "(";
    private static final char TODO_TASK_TYPE = 'T';
    private static final char DEADLINE_TASK_TYPE = 'D';
    private static final char EVENT_TASK_TYPE = 'E';
    private final File file;

    public FileManager() {
        this.file = new File("./data/duke.txt");
    }

    public List<Task> getTasks() throws InvalidDataException {
        try {
            Scanner sc = new Scanner(this.file);
            List<Task> taskList = new ArrayList<Task>();
            if (this.file.exists()) {
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    String[] taskDetails = task.split(DATA_TOKEN_SEPARATOR);
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
        char taskType = taskDescription.charAt(1);
        int taskStatus = taskDescription.charAt(5) == ' ' ? 0 : 1;
        String taskTitle = taskDescription.split(TASK_DATE_SEPARATOR)[0].substring(8);
        return taskType + DATA_TOKEN_SEPARATOR + taskStatus + DATA_TOKEN_SEPARATOR + taskTitle;
    }

    public void saveTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        fw.write(convertTaskToText(task) + System.lineSeparator());
        fw.close();
    }
}
