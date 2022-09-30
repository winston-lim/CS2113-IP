package command;

import java.io.IOException;
import exception.InsufficentArgumentsException;
import exception.InvalidTimeFormatException;
import exception.TaskNotFoundException;

interface CommandInterface {
    boolean executeCommand() throws TaskNotFoundException, IOException,
            InsufficentArgumentsException, InvalidTimeFormatException;
}
