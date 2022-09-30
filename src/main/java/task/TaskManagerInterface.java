package task;

import java.io.IOException;

import exception.TaskNotFoundException;

interface TaskManagerInterface {
    public void listTasks();

    public void addTask(Task task) throws IOException;

    public void deleteTask(int id) throws TaskNotFoundException, IOException;

    public void markTask(int id) throws TaskNotFoundException, IOException;

    public void unmarkTask(int id) throws TaskNotFoundException, IOException;
}
