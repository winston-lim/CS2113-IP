package command;

import java.io.IOException;
import exception.InsufficentArgumentsException;
import exception.TaskNotFoundException;
import exception.InvalidTimeFormatException;

interface CommandInterface {
    boolean executeCommand() throws TaskNotFoundException, IOException,
            InsufficentArgumentsException, InvalidTimeFormatException;
}
