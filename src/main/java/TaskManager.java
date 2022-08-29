import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> recordedTasks = new ArrayList<Task>();
    private int taskCount = 0;

    /**
     * Creates a new task.
     * 
     * @param description description that the new task is created with
     * @return String response that acts as feedback for users
     */
    public String addTask(String description) {
        recordedTasks.add(new Task(description));
        taskCount++;
        return "added " + description;
    }

    /**
     * Marks a task as done by calling Task.setIsDone.
     * 
     * @param taskNum a unique identifier number for task to upate
     * @return String success or error message that acts as feedback for user
     */
    public String markTask(int taskNum) {
        if (taskNum < 1 || taskNum > recordedTasks.size()) { // task does not exist
            return "Error: Task does not exist";
        } else if (recordedTasks.get(taskNum - 1).getStatus()) {
            return "Error: Task is already marked";
        } else { // task exists
            recordedTasks.get(taskNum - 1).setIsDone(true);
            return "Marked " + recordedTasks.get(taskNum - 1).getDescription();
        }
    }

    /**
     * Marks a task as not done by calling Task.setIsDone.
     * 
     * @param taskNum a unique identifier number for task to upate
     * @return String success or error message that acts as feedback for user
     */
    public String unmarkTask(int taskNum) {
        if (taskNum < 1 || taskNum > recordedTasks.size()) { // task does not exist
            return "Error: Task does not exist";
        } else if (!recordedTasks.get(taskNum - 1).getStatus()) {
            return "Error: Task is not marked and cannot be unmarked";
        } else { // task exists
            recordedTasks.get(taskNum - 1).setIsDone(false);
            return "Unmarked " + recordedTasks.get(taskNum - 1).getDescription();
        }
    }

    public List<Task> getRecordedTasks() {
        return recordedTasks;
    }

    public void setRecordedTasks(List<Task> recordedTasks) {
        this.recordedTasks = recordedTasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
}
