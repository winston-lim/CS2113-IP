package command;

import exception.CommandNotFoundException;
import exception.InsufficentArgumentsException;
import exception.TaskNotFoundException;

interface CommandInterface {
    void handleCommand()
            throws CommandNotFoundException, InsufficentArgumentsException, TaskNotFoundException;
}
