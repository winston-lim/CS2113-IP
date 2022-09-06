interface CommandInterface {
    void handleCommand()
            throws CommandNotFoundException, InsufficentArgumentsException, TaskNotFoundException;
}
