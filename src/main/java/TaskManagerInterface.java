interface TaskManagerInterface {
    public void listTasks();

    public void addTask(String task, String[] args) throws InsufficentArgumentsException;

    public void markTask(String[] args) throws InsufficentArgumentsException, TaskNotFoundException;

    public void unmarkTask(String[] args)
            throws InsufficentArgumentsException, TaskNotFoundException;
}
