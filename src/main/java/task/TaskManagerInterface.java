package task;

import java.io.IOException;

import exception.InsufficentArgumentsException;
import exception.TaskNotFoundException;

interface TaskManagerInterface {
    public void listTasks();

    public void addTask(String task, String[] args)
            throws InsufficentArgumentsException, IOException;

    public void deleteTask(String[] args)
            throws InsufficentArgumentsException, TaskNotFoundException, IOException;

    public void markTask(String[] args)
            throws InsufficentArgumentsException, TaskNotFoundException, IOException;

    public void unmarkTask(String[] args)
            throws InsufficentArgumentsException, TaskNotFoundException, IOException;
}
